package com.clwater.language_study

import kotlin.random.Random

/**
 * @author: gengzhibo
 * @date: 2022/4/2
 */
class Constants {
    companion object {
        val SERVER_URL = "http://localhost:5000"
        val Image_URL = "https://source.unsplash.com/900x1600/?white"
            get() = field + "/" + Random(System.currentTimeMillis()).nextInt(10000000)
    }
}