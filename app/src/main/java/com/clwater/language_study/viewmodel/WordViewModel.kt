package com.clwater.language_study.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.clwater.language_study.enity.WordEnity
import com.clwater.language_study.http.WordListModel
import com.clwater.language_study.manager.FuelManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * @author: gengzhibo
 * @date: 2022/4/2
 */
class WordViewModel : ViewModel(){
    val job = Job()
    val ioScope = CoroutineScope(Dispatchers.IO + job)


    var wordList: MutableLiveData<List<WordEnity>> = MutableLiveData<List<WordEnity>>(listOf())
    var error: MutableLiveData<HashMap<String, String>>  = MutableLiveData<HashMap<String, String>>()

    fun getWordList(index: Int, max: Int ){
        ioScope.launch {
            val result = FuelManager.getWordList(0)
                val wordListModel =
                    Gson().fromJson<WordListModel>(result.toString(), object: TypeToken<WordListModel>() {}.type)
                wordList.postValue(wordListModel.data)
//            }
        }
    }
}
