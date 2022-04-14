package com.clwater.language_study.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.clwater.language_study.Constants
import com.clwater.language_study.enity.WordEnity
import com.clwater.language_study.http.WordListModel
import com.clwater.language_study.http.WordRandomModel
import com.clwater.language_study.manager.FuelManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * @author: gengzhibo
 * @date: 2022/4/11
 */
class MainViewModel: ViewModel() {
    val job = Job()
    val ioScope = CoroutineScope(Dispatchers.IO + job)


    var UI = MutableLiveData(UIView())

    class UIView{
        var mainVBackgroundUrl: MutableLiveData<String> = MutableLiveData(Constants.Image_URL)
        var randomWord: MutableLiveData<WordEnity> =  MutableLiveData(WordEnity(0, "", "", "", "", "", 0, ""))
    }

    fun changeNewBackgroundUrl(){
        UI.value?.mainVBackgroundUrl?.value = Constants.Image_URL
        UI.postValue(UI.value)
    }

    fun getRandomWord(){
        ioScope.launch {
            val result = FuelManager.getWordRandom()

            val wordEnity =
                Gson().fromJson<WordRandomModel>(result.toString(), object: TypeToken<WordRandomModel>() {}.type)
            UI.value?.randomWord?.postValue(wordEnity.data)
            UI.postValue(UI.value)
        }
    }

}