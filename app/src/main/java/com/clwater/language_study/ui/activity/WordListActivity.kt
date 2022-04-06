package com.clwater.language_study.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
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

/**
 * @author: gengzhibo
 * @date: 2022/4/6
 */
class WordListActivity : BaseActivity() {
    private val wordViewModel : WordViewModel by viewModels()

    companion object{
        fun start(context: Context){
            val intent = Intent(context, WordListActivity::class.java)
            context.startActivity(intent)
        }
    }

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

    override fun initData() {
    }

    override fun initView() {
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