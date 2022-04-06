package com.clwater.language_study.manager

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author: gengzhibo
 * @date: 2022/3/17
 */
//服务器获取token信息
object  FuelManager {

    suspend fun getWordList(page: Int): Any {
        return withContext(Dispatchers.IO) {

            val (request, response, result) = Fuel.post("http://192.168.7.36:5000/api/word/list",
                listOf("page" to page))
                .responseString()

            when (result) {
                is Result.Failure -> {
                    return@withContext String(response.data)
                }
                is Result.Success -> {
                    val data = result.value
                    return@withContext data
                }
            }
        }
    }


}