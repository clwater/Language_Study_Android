package com.clwater.language_study

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.clwater.language_study.enity.WordEnity
import com.clwater.language_study.manager.FuelManager
import com.clwater.language_study.ui.theme.Language_StudyTheme
import com.clwater.language_study.viewmodel.WordViewModel
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val wordViewModel :WordViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()


        wordViewModel.wordList.observe(this){
            Log.d("gzb", "wordViewModel.wordList: " + Gson().toJson(it))
            initView()
//            Log.d("gzb", it[0].word)
        }
        wordViewModel.getWordList(1,1)

    }

    private fun initView() {
        setContent {
            Language_StudyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting(wordViewModel.wordList.value!!)
                }
            }
        }
    }

    @Composable
    fun Greeting(list: List<WordEnity>) {
        Log.d("gzb", Gson().toJson(list))
        Column() {
            LazyColumn {
                list.forEach { item ->
                    item{
                        Text(text = item.word)
                    }
                }
            }
        }
    }
}



