package com.vad.pullup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vad.pullup.data.ExerciseRepository
import kotlinx.coroutines.launch

class ExerciseViewModel(private val repository: ExerciseRepository) : ViewModel() {
    private var count = 10

    var countOfRepeat: MutableLiveData<Int> = MutableLiveData()

    fun increaseCount() =
        count++

    fun decreaseCount() =
        count--

    fun saveCount() = viewModelScope.launch {
        repository.writeExercise()
    }

    fun getExerciseByDay(day: Int) = viewModelScope.launch {
        repository.getPlanOfDay(day)
    }
}