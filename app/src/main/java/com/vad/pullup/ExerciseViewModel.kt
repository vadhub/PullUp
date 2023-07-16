package com.vad.pullup

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vad.pullup.data.ExerciseRepository
import com.vad.pullup.data.Timer
import com.vad.pullup.data.db.Exercise
import com.vad.pullup.data.db.ExercisePlan
import kotlinx.coroutines.launch

class ExerciseViewModel(private val repository: ExerciseRepository) : ViewModel() {

    private var timerHandle: Timer? = null
    private var state = 0
    private var switchTimer = true
    private var listOfCount: List<Int> = listOf()
    private var listOfExercise: List<ExercisePlan> = listOf()

    val countOfRepeat: MutableLiveData<Int> = MutableLiveData()
    val exercisePlan: MutableLiveData<ExercisePlan> = MutableLiveData()
    val listCount: MutableLiveData<List<Int>> = MutableLiveData()
    val changeTimeout: MutableLiveData<Boolean> = MutableLiveData()
    val timer: MutableLiveData<Timer> = MutableLiveData()
    val stateLiveData: MutableLiveData<Int> = MutableLiveData()

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

        if (listOfCount.size - 2 > state) {
            changeTimeout.postValue(switchTimer)

            timerHandle = Timer(10_000)

            timer.postValue(timerHandle!!)
        }
    }

    fun switchState() {
        if (listOfCount.size - 1 > state) state++
        exercisePlan.postValue(listOfExercise[state])
        stateLiveData.postValue(state)
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