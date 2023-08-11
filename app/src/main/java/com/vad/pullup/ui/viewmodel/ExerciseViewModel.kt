package com.vad.pullup.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vad.pullup.data.ExerciseRepository
import com.vad.pullup.domain.model.entity.ProgramItem
import com.vad.pullup.domain.model.entity.Repeat
import com.vad.pullup.domain.model.RepeatSum
import com.vad.pullup.domain.model.Timer
import com.vad.pullup.domain.model.TimerHandler
import com.vad.pullup.domain.model.entity.Exercise
import com.vad.pullup.domain.model.entity.ExercisePlan
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExerciseViewModel(private val repository: ExerciseRepository) : ViewModel() {

    private var timerHandle: Timer? = null
    private var state = 0
    private var listOfCount: List<Int> = listOf()
    private var listOfExercise: List<ExercisePlan> = listOf()
    private var sum = 0

    val countOfRepeat: MutableLiveData<Int> = MutableLiveData()
    val exercisePlan: MutableLiveData<ExercisePlan> = MutableLiveData()
    val listCount: MutableLiveData<List<Int>> = MutableLiveData()
    val changeTimeout: MutableLiveData<Boolean> = MutableLiveData()
    val timer: MutableLiveData<Timer> = MutableLiveData()
    val repeat: MutableLiveData<Int> = MutableLiveData()
    val finish: MutableLiveData<Int> = MutableLiveData()
    val sumRepeat: MutableLiveData<List<RepeatSum>> = MutableLiveData()
    val allProgram: MutableLiveData<List<ProgramItem>> = MutableLiveData()
    val reset: MutableLiveData<Boolean> = MutableLiveData()

    fun setProgram(listRepeat: List<Repeat>) = viewModelScope.launch {
        repository.setAllProgram(listRepeat)
        repository.setAllProgramItem()
    }

    fun increaseCount(count: Int) {
        countOfRepeat.postValue(count+1)
    }

    fun decreaseCount(count: Int) {
        if (count > 0) countOfRepeat.postValue(count-1)
    }

    fun saveCount(exercise: Exercise, timerHandler: TimerHandler) = viewModelScope.launch {

        repository.writeExercise(exercise)
        sum += exercise.count

        Log.d("##saveCount", "$state")

        if (listOfCount.size - 1 > state) {
            changeTimeout.postValue(true)
            startTimer(120_000, timerHandler)
        } else {
            finish.postValue(sum)
        }
    }

    fun switchState() {
        if (listOfCount.size - 1 > state) {
            state++
        }

        exercisePlan.postValue(listOfExercise[state])
        repeat.postValue(state)
    }

    fun getListOfCountExercise(week: Int) = viewModelScope.launch {
        listOfCount = repository.getPlanOfWeek(week).map { it.count }
        listCount.postValue(listOfCount)
    }

    fun getExerciseByWeek(week: Int) = viewModelScope.launch {
        listOfExercise = repository.getPlanOfWeek(week)
        Log.d("week", "$week")
        exercisePlan.postValue(listOfExercise[state])
    }

    fun deleteAllProgram() = viewModelScope.launch {
        repository.delete()
    }

    fun getSumRepeat() = viewModelScope.launch {
        sumRepeat.postValue(repository.getSumRepeatGroupByDate())
    }

    fun getAllProgram() = viewModelScope.launch(Dispatchers.IO) {
        allProgram.postValue(repository.getAllItemProgram())
    }

    fun setTimer(increase: Boolean, timerHandler: TimerHandler) {
        val time = timerHandle?.time ?: 10_000
        var change = 1

        if (!increase) {
            change *= -1
        }

        startTimer(time + 10_000 * change, timerHandler)
    }

    fun skipTimer() {
        timerHandle?.cancelTimer()
        timerHandle = null
    }

    fun startTimer(time: Long, timerHandler: TimerHandler) {
        timerHandle?.cancelTimer()
        timerHandle = null
        timerHandle = Timer(time)
        timerHandle!!.setTimerHandler(timerHandler)
        timerHandle!!.startTimer()
        timer.postValue(timerHandle!!)
    }

    fun resetAll() {
        sum = 0
        state = 0
        listOfCount = listOf()
        reset.postValue(true)
        repeat.postValue(state)
    }

}