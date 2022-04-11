package com.clwater.language_study.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.clwater.language_study.Constants
import com.clwater.language_study.enity.WordEnity

/**
 * @author: gengzhibo
 * @date: 2022/4/11
 */
class ImageViewModel : ViewModel() {

    var mainBgUrl : MutableLiveData<String> = MutableLiveData(Constants.Image_URL)

    fun getNewMainBg(){
        mainBgUrl.value = Constants.Image_URL
    }
}