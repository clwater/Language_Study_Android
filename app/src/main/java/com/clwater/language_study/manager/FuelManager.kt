package com.clwater.language_study.manager

import android.util.Log
import com.clwater.language_study.Constants
import com.clwater.language_study.enity.WordEnity
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author: gengzhibo
 * @date: 2022/3/17
 */
//服务器获取token信息
object  FuelManager {

    suspend fun getWordList(): Any {
        return withContext(Dispatchers.IO) {

            val (request, response, result) = Fuel.get("http://192.168.7.36:5000/api/word/list")
//                .header("Accept", "application/json")
                .responseString()
//            Log.d("gzb", "result" + Gson().toJson(result))

            when (result) {
                is Result.Failure -> {
                    return@withContext String(response.data)
                }
                is Result.Success -> {
                    val data = result.value
//                    Log.d("gzb", "Inside the Fuel Data Success result $data")
//                    val list : List<WordEnity>  = result.get()
                    return@withContext data // response.statusCode is also available if we need to go that path.
                }
            }
        }
    }


}