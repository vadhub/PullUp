package com.vad.pullup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vad.pullup.data.ExerciseRepository
import com.vad.pullup.data.db.Exercise
import com.vad.pullup.data.db.ExercisePlan
import kotlinx.coroutines.launch

class ExerciseViewModel(private val repository: ExerciseRepository) : ViewModel() {

    private var state = 1

    val countOfRepeat: MutableLiveData<Int> = MutableLiveData()
    val exercisePlan: MutableLiveData<List<ExercisePlan>> = MutableLiveData()
    val listCount: MutableLiveData<List<Int>> = MutableLiveData()

    fun setProgram() = viewModelScope.launch {
        repository.setAllProgram()
    }

    fun increaseCount(count: Int) = countOfRepeat.postValue(count+1)

    fun decreaseCount(count: Int) {
        if (count > 0) countOfRepeat.postValue(count-1)
    }

    fun saveCount(exercise: Exercise) = viewModelScope.launch {
        repository.writeExercise(exercise)
        state++
    }

    fun getListOfCountExercise(day: Int) = viewModelScope.launch {
        listCount.postValue(repository.getPlanOfDay(day).map { it.count })
    }

    fun getExerciseByDay(day: Int) = viewModelScope.launch {
        exercisePlan.postValue(repository.getPlanOfDay(day))
    }
}