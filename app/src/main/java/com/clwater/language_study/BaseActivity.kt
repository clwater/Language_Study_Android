package com.clwater.language_study

import android.os.Bundle
import androidx.activity.ComponentActivity

/**
 * @author: gengzhibo
 * @date: 2022/4/6
 */
abstract class BaseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        initView()
        initData()
    }

    abstract fun initData()

    abstract fun initView()
}