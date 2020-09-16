package com.ryan.github.kndroid.practice.type

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.io.File

interface GithubApi {
    @GET("/repos/{owner}/{repo}")
    fun getRepository(@Path("owner") owner: String, @Path("repo") repo: String): Call<Repository>
}

suspend fun retrofitRequest() {
    withContext(Dispatchers.IO) {
        doRequest()
    }
}

suspend fun doRequest() {
    // 创建Retrofit对象
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    // 通过GithubApi::class.java来获取class对象
    val githubApi = retrofit.create(GithubApi::class.java)
    // 提交请求
    val response = githubApi.getRepository("Jetbrains", "kotlin").execute()
    // repository的类型为Repository?，即有可能为null
    val repository = response.body()
    if (repository == null) {
        return print("error! code: ${response.code()} message: ${response.message()}")
    } else {
        // 智能类型转换，将Repository?转换为Repository
        println("name: ${repository.name}")
        println("full_name: ${repository.full_name}")
        println("owner.login: ${repository.owner.login}")

        // Kotlin的扩展函数，扩展了File类, 增加了writeText方法
        File("kotlin.html").writeText(
            """
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="UTF-8">
                <title>${repository.owner.login} - ${repository.name}</title>
            </head>
            <body>
                <h1><a href='${repository.html_url}'>${repository.owner.login} - ${repository.name}</a></h1>
                <p>Stars: ${repository.stargazers_count}</p>
                <p>Forks: ${repository.forks_count}</p>
            </body>
            </html>
        """.trimIndent()
        )
    }
}