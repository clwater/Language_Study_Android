package com.clwater.language_study.ui.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.graphics.Color
import com.clwater.language_study.BaseActivity
import com.clwater.language_study.Constants
import com.clwater.language_study.R
import com.clwater.language_study.ui.theme.Language_StudyTheme

class MainActivity : BaseActivity() {
    var bgImageUrl: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initData() {
//        WordListActivity.start(this)
    }

    override fun initView() {
        setContent {
            Language_StudyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainActivityView()
                }
            }
        }
    }

    @Composable
    private fun MainActivityView() {
        val bgImageModifier = Modifier.fillMaxSize()
        Image(painter = painterResource(id = R.mipmap.bg_main), contentDescription = "",
            modifier = bgImageModifier, contentScale = ContentScale.Crop)
        Box(modifier = Modifier.background(Color(0xB0666666))) {
                Button(onClick = {
                    bgImageUrl = Constants.Image_URL
                }, modifier = Modifier.align(Alignment.BottomStart)) {
                    Text(text = "r")
                }

        }

    }

}



