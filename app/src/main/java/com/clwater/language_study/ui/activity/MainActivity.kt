package com.clwater.language_study.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.core.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.clwater.language_study.BaseActivity
import com.clwater.language_study.Constants
import com.clwater.language_study.R
import com.clwater.language_study.ui.theme.Language_StudyTheme
import com.clwater.language_study.viewmodel.MainViewModel
import com.clwater.language_study.viewmodel.WordViewModel
import com.google.gson.Gson

class MainActivity : BaseActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    private val wordViewModel: WordViewModel by viewModels()

    private var loadingBackground = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initData() {
        mainViewModel.UI.observe(this) {
            Log.d("gzb", "11111" + Gson().toJson(it.randomWord.value))

            mainView(mainViewModel.UI.value!!)
        }
        mainViewModel.getRandomWord()
    }

    private fun mainView(UI: MainViewModel.UIView) {
        setContent {
            Language_StudyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainActivityView(UI)
                }
            }
        }
    }

    override fun initView() {
        mainViewModel.UI.value?.let { mainView(it) }
    }


    @Composable
    private fun MainActivityView(UI: MainViewModel.UIView) {
        val bgImageModifier = Modifier.fillMaxSize()
        Image(
            painter = painterResource(id = R.mipmap.bg_main), contentDescription = "",
            modifier = bgImageModifier, contentScale = ContentScale.Crop
        )
        val painter = rememberAsyncImagePainter(UI.mainVBackgroundUrl.value)
        Image(
            painter = painter, contentDescription = "",
            modifier = bgImageModifier, contentScale = ContentScale.Crop
        )
        when (painter.state) {
            is AsyncImagePainter.State.Loading -> {
                loadingBackground.value = true
            }
            else -> {
                loadingBackground.value = false
            }
        }
        Box(
            modifier =
            Modifier
        ) {
            UI.randomWord.value?.let {
                Box(modifier = Modifier.align(Alignment.Center)
                    .fillMaxWidth(0.5f)
                    .background(Color(0x997E7E7E))
                    .clickable { Toast.makeText(this@MainActivity, "1111", Toast.LENGTH_SHORT) }
                    .clip(RoundedCornerShape(8.dp, 8.dp, 0.dp, 0.dp))
                    .padding(12.dp)
                ) {
                    Text(text = it.word, modifier = Modifier.align(Alignment.Center))
                }
            }
            Button(onClick = {
                mainViewModel.changeNewBackgroundUrl()
            }, modifier = Modifier.align(Alignment.BottomStart)) {
                val rotation = infiniteRotation(loadingBackground.value)
                Image(painter = painterResource(id = R.drawable.ic_baseline_refresh_24),
                    contentDescription = "",
                    modifier = Modifier.graphicsLayer {
                        rotationZ = rotation.value
                    }
                )
            }


            Button(
                onClick = { WordListActivity.start(this@MainActivity) },
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_list_alt_24),
                    contentDescription = null,
                )
            }


        }

    }

    @Composable
    private fun infiniteRotation(
        startRotate: Boolean,
    ): Animatable<Float, AnimationVector1D> {
        var rotation by remember { mutableStateOf(Animatable(0f)) }
        LaunchedEffect(key1 = startRotate, block = {
            if (startRotate) {
                //从上次的暂停角度 -> 执行动画 -> 到目标角度（+360°）
                rotation.animateTo(
                    (rotation.value % 360f) + 360f, animationSpec = infiniteRepeatable(
                        animation = tween(1 * 1000, easing = LinearEasing)
                    )
                )
            } else {
                rotation.stop()
                //初始角度取余是为了防止每次暂停后目标角度无限增大
                rotation = Animatable(rotation.value % 360f)
            }
        })
        return rotation
    }
}



