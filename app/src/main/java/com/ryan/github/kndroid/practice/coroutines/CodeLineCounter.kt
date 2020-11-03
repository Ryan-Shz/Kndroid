package com.ryan.github.kndroid.practice.coroutines

import com.ryan.github.kndroid.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.selects.select
import kotlinx.coroutines.withContext
import java.io.File
import java.util.concurrent.Executors

// 案例：统计代码行数
suspend fun main() {
    val result = lineCounter(File("."))
    log(result)
}

suspend fun lineCounter(root: File): Map<File, Int> {
    return Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1)
        .asCoroutineDispatcher()
        .use {
            withContext(it) {
                val fileChannel = walkFile(root)
                val fileLinesChannels = List(5) {
                    fileLineCounter(fileChannel)
                }
                resultAggregator(fileLinesChannels)
            }
        }
}

suspend fun resultAggregator(channels: List<ReceiveChannel<FileLines>>): Map<File, Int> {
    val map = HashMap<File, Int>()
    channels.aggregate { filteredChannels ->
        select<FileLines?> {
            filteredChannels.forEach { it ->
                it.onReceiveOrNull {
                    log("received: $it")
                    it
                }
            }
        }?.let {
            map[it.file] = it.lines
        }
    }
    return map
}

tailrec suspend fun List<ReceiveChannel<FileLines>>.aggregate(block: suspend (List<ReceiveChannel<FileLines>>) -> Unit) {
    block(this)
    filter { !it.isClosedForReceive }.takeIf { it.isNotEmpty() }?.aggregate(block)
}

suspend fun CoroutineScope.fileLineCounter(input: ReceiveChannel<File>): ReceiveChannel<FileLines> {
    return produce(capacity = Channel.BUFFERED) {
        for (file in input) {
            file.useLines {
                send(FileLines(file, it.count()))
            }
        }
    }
}

fun CoroutineScope.walkFile(file: File): ReceiveChannel<File> {
    return produce(capacity = Channel.BUFFERED) {
        fileWalker(file)
    }
}

suspend fun SendChannel<File>.fileWalker(file: File) {
    if (file.isDirectory) {
        file.listFiles()?.filter(fileFilter)?.forEach { fileWalker(it) }
    } else {
        send(file)
    }
}

val fileFilter = { file: File ->
    file.isDirectory || file.name.endsWith(".kt")
}

data class FileLines(val file: File, val lines: Int)

