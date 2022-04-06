package com.clwater.language_study.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.clwater.language_study.BaseActivity
import com.clwater.language_study.enity.WordEnity
import com.clwater.language_study.ui.theme.Language_StudyTheme
import com.clwater.language_study.viewmodel.WordViewModel
import com.google.gson.Gson

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initData() {
        WordListActivity.start(this)
    }

    override fun initView() {
    }

}



