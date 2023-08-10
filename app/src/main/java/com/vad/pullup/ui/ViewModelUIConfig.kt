package com.vad.pullup.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelUIConfig: ViewModel() {

    val visibleNavBar: MutableLiveData<Boolean> = MutableLiveData()

    fun visibleNavigationBar(isVisible: Boolean) {
        visibleNavBar.postValue(isVisible)
    }

}