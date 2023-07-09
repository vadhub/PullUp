package com.vad.pullup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vad.pullup.data.Exercise
import com.vad.pullup.data.ExercisePlan
import com.vad.pullup.data.ExerciseRepository
import kotlinx.coroutines.launch

class ExerciseViewModel(private val repository: ExerciseRepository) : ViewModel() {
    private var count = 10

    var countOfRepeat: MutableLiveData<Int> = MutableLiveData()
    var exercisePlan: MutableLiveData<ExercisePlan> = MutableLiveData()

    fun increaseCount() =
        count++

    fun decreaseCount() =
        count--

    fun saveCount(exercise: Exercise) = viewModelScope.launch {
        repository.writeExercise(exercise)
    }

    fun getExerciseByDay(day: Int) = viewModelScope.launch {
        exercisePlan.postValue(repository.getPlanOfDay(day))
    }
}