package com.clwater.language_study.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.clwater.language_study.Constants

/**
 * @author: gengzhibo
 * @date: 2022/4/11
 */
class MainViewModel: ViewModel() {
    var UI = MutableLiveData(UIView())

    class UIView{
        var mainVBackgroundUrl: MutableLiveData<String> = MutableLiveData(Constants.Image_URL)
    }

    fun changeNewBackgroundUrl(){
        UI.value?.mainVBackgroundUrl?.value = Constants.Image_URL
        UI.postValue(UI.value)
    }
}