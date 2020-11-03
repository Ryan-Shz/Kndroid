package com.ryan.github.kndroid.practice.coroutines

import com.ryan.github.kndroid.log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.launch

suspend fun main() {
//    channelTest()
//    produceChannelTest()
    broadcastChannelTest()
}

private suspend fun channelTest() {
    //    val channel = Channel<Int>(Channel.RENDEZVOUS) // 默认，发送后必须等接收者接收后才能发送下一个，否则就挂起
//     val channel = Channel<Int>(Channel.UNLIMITED) // 无限容量，可以往通道里放无限数据，不管有没有接受者
//     val channel = Channel<Int>(Channel.CONFLATED) // 每次只保存发送的最新的值，之前的数据会被抛弃
//    val channel = Channel<Int>(Channel.BUFFERED) // 固定容量，由虚拟机参数执行
    val channel = Channel<Int>(1) // 自定义容量
    val producer = GlobalScope.launch {
        for (i in 0..3) {
            channel.send(i)
            log("send $i")
        }
        channel.close()
    }

    val consumer = GlobalScope.launch {
        while (!channel.isClosedForReceive) {
            val value = channel.receiveOrNull()
            log("received: $value")
        }
    }

    producer.join()
    consumer.join()
}

private suspend fun produceChannelTest() {
    val producer = GlobalScope.produce<Int>(capacity = Channel.UNLIMITED) {
        for (i in 0..3) {
            send(i)
            log("send $i")
        }
        close()
    }
    val consumer = GlobalScope.launch {
        for (i in producer) {
            log("received: $i")
        }
    }
    consumer.join()

}

private suspend fun broadcastChannelTest() {
    val channel = Channel<Int>()
    // 通过channel.broadcast创建
//    val broadcastChannel = channel.broadcast()
//    val job1 = GlobalScope.launch {
//        for (i in 0..3) {
//            broadcastChannel.send(i)
//            log("boardcast $i")
//        }
//    }
    // 通过Scope.broadcast创建, 不支持 RENDEZVOUS 类型
    val broadcastChannel = GlobalScope.broadcast(capacity = Channel.BUFFERED) {
        for (i in 0..3) send(i)
    }

    val job1 = GlobalScope.launch {
        val receiveChannel = broadcastChannel.openSubscription()
        for (i in receiveChannel) {
            log("received1: $i")
        }
    }

    val job2 = GlobalScope.launch {
        val receiveChannel = broadcastChannel.openSubscription()
        for (i in receiveChannel) {
            log("received2: $i")
        }
    }

    val job3 = GlobalScope.launch {
        val receiveChannel = broadcastChannel.openSubscription()
        for (i in receiveChannel) {
            log("received3: $i")
        }
    }

    job1.join()
    job2.join()
    job3.join()
}