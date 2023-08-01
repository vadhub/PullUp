package com.vad.pullup

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vad.pullup.data.ConverterProgram
import com.vad.pullup.data.ExerciseRepository
import com.vad.pullup.data.ProgramItem
import com.vad.pullup.data.Repeat
import com.vad.pullup.data.RepeatSum
import com.vad.pullup.data.Timer
import com.vad.pullup.data.TimerHandler
import com.vad.pullup.data.db.Exercise
import com.vad.pullup.data.db.ExercisePlan
import kotlinx.coroutines.launch

class ExerciseViewModel(private val repository: ExerciseRepository) : ViewModel() {

    private var timerHandle: Timer? = null
    private var state = 0
    private var switchTimer = true
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

    fun setProgram(listRepeat: List<Repeat>) = viewModelScope.launch {
        repository.setAllProgram(listRepeat)
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

        timerHandle?.cancelTimer()
        timerHandle = null

        if (listOfCount.size - 1 > state) {
            changeTimeout.postValue(switchTimer)
            startTimer(30_000, timerHandler)
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
        exercisePlan.postValue(listOfExercise[state])
    }

    fun deleteAllProgram() = viewModelScope.launch {
        repository.delete()
    }

    fun getSumRepeat() = viewModelScope.launch {
        sumRepeat.postValue(repository.getSumRepeatGroupByDate())
    }

    fun getAllProgram() = viewModelScope.launch {
        allProgram.postValue(ConverterProgram.convertToListProgram(repository.getAllProgram(), 30))
    }

    fun setTimer(increase: Boolean, timerHandler: TimerHandler) {
        val time = timerHandle?.time ?: 10_000
        timerHandle?.cancelTimer()
        timerHandle = null
        var change = 1

        if (!increase) {
            change *= -1
        }

        timerHandle = Timer(time + 10_000 * change)
        timerHandle?.setTimerHandler(timerHandler)
        timerHandle?.startTimer()
    }

    fun skipTimer() {
        timerHandle?.cancelTimer()
        timerHandle = null
    }

    fun startTimer(time: Long, timerHandler: TimerHandler) {
        timerHandle?.cancelTimer()
        timerHandle = Timer(time)
        timerHandle!!.setTimerHandler(timerHandler)
        timerHandle!!.startTimer()
        timer.postValue(timerHandle!!)
    }

}