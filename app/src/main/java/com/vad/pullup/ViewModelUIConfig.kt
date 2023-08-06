package com.vad.pullup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelUIConfig: ViewModel() {

    val visibleNavBar: MutableLiveData<Boolean> = MutableLiveData()

    fun visibleNavigationBar(isVisible: Boolean) {
        visibleNavBar.postValue(isVisible)
    }

}