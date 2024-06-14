package com.livecoding.ni

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    val stateMutableLiveData = MutableLiveData<String>()
    val stateLiveData: LiveData<String> = stateMutableLiveData


    fun setState(state: Boolean) {
        stateMutableLiveData.value = if (state) "Success" else "Failure"
    }


}