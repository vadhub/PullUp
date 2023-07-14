package com.vad.pullup

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vad.pullup.data.ExerciseRepository
import com.vad.pullup.data.db.Exercise
import com.vad.pullup.data.db.ExercisePlan
import kotlinx.coroutines.launch

class ExerciseViewModel(private val repository: ExerciseRepository) : ViewModel() {

    private var state = 0
    private var listOfCount: List<Int> = listOf()
    private var listOfExercise: List<ExercisePlan> = listOf()

    val countOfRepeat: MutableLiveData<Int> = MutableLiveData()
    val exercisePlan: MutableLiveData<ExercisePlan> = MutableLiveData()
    val listCount: MutableLiveData<List<Int>> = MutableLiveData()

    fun setProgram() = viewModelScope.launch {
        repository.setAllProgram()
    }

    fun increaseCount(count: Int) {
        countOfRepeat.postValue(count+1)
    }

    fun decreaseCount(count: Int) {
        if (count > 0) countOfRepeat.postValue(count-1)
    }

    fun saveCount(exercise: Exercise) = viewModelScope.launch {
//        repository.writeExercise(exercise)
        if (listOfCount.size - 1 > state) state++
        exercisePlan.postValue(listOfExercise[state])
    }

    fun getListOfCountExercise(day: Int) = viewModelScope.launch {
        listOfCount = repository.getPlanOfDay(day).map { it.count }
        listCount.postValue(listOfCount)
    }

    fun getExerciseByDay(day: Int) = viewModelScope.launch {
        listOfExercise = repository.getPlanOfDay(day)
        exercisePlan.postValue(listOfExercise[state])
    }
}