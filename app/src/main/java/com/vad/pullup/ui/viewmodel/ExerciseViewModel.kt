package com.vad.pullup.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vad.pullup.data.ExerciseRepository
import com.vad.pullup.data.SaveInterrupted
import com.vad.pullup.domain.model.ObjectAndRepeat
import com.vad.pullup.domain.model.Timer
import com.vad.pullup.domain.model.TimerHandler
import com.vad.pullup.domain.model.entity.Exercise
import com.vad.pullup.domain.model.entity.ExercisePlan
import com.vad.pullup.domain.model.entity.ProgramItem
import com.vad.pullup.domain.model.entity.Repeat
import com.vad.pullup.domain.model.entity.RepeatSum
import com.vad.pullup.domain.model.entity.TotalResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExerciseViewModel(private val repository: ExerciseRepository) : ViewModel() {

    private var timerHandle: Timer? = null
    private var state = 0
    private var listOfCount: List<Int> = listOf()
    private var listOfExercise: List<ExercisePlan> = listOf()
    private var sum = 0
    private var max = 0
    private var min = 100

    val countOfRepeat: MutableLiveData<ObjectAndRepeat<Int>> = MutableLiveData()
    val exercisePlan: MutableLiveData<ObjectAndRepeat<ExercisePlan>> = MutableLiveData()
    val listCount: MutableLiveData<List<Int>> = MutableLiveData()
    val changeTimeout: MutableLiveData<Boolean> = MutableLiveData()
    val timer: MutableLiveData<Timer> = MutableLiveData()
    val repeat: MutableLiveData<Int> = MutableLiveData()
    val finish: MutableLiveData<TotalResult> = MutableLiveData()
    val sumRepeat: MutableLiveData<List<RepeatSum>> = MutableLiveData()
    val allProgram: MutableLiveData<List<ProgramItem>> = MutableLiveData()
    val allExercise: MutableLiveData<List<Exercise>> = MutableLiveData()

    fun setState(state: Int) {
        this.state = state
        repeat.postValue(state)
    }

    fun setProgram(listRepeat: List<Repeat>) = viewModelScope.launch {
        repository.setAllProgram(listRepeat)
        repository.setAllProgramItem()
    }

    fun increaseCount(count: Int) {
        countOfRepeat.postValue(ObjectAndRepeat(count + 1, state))
    }

    fun decreaseCount(count: Int) {
        if (count > 0) countOfRepeat.postValue(ObjectAndRepeat(count - 1, state))
    }

    fun saveCount(exercise: Exercise, timerHandler: TimerHandler) = viewModelScope.launch {

        repository.writeExercise(exercise)
        sum += exercise.count

        if (exercise.count > max) {
            max = exercise.count
        }

        if (exercise.count < min) {
            min = exercise.count
        }

        Log.d("##saveCount", "$state")

        if (listOfCount.size - 1 > state) {
            changeTimeout.postValue(true)
            startTimer(120_000, timerHandler)
        } else {
            reset()
            finish.postValue(TotalResult(max, min, sum))
        }
    }

    fun switchState() {
        Log.d("#switchState", "switchState")
        if (listOfCount.size - 1 > state) {
            state++
        }
        exercisePlan.postValue(ObjectAndRepeat(listOfExercise[state], state))
        repeat.postValue(state)
    }

    fun getListOfCountExercise(week: Int) = viewModelScope.launch {
        listOfCount = repository.getPlanOfWeek(week).map { it.count }
        listCount.postValue(listOfCount)
    }

    fun getPlanByWeek(week: Int) = viewModelScope.launch {
        listOfExercise = repository.getPlanOfWeek(week)
        Log.d("#week", "$week")
        exercisePlan.postValue(ObjectAndRepeat(listOfExercise[state], state))
    }

    /*get all exercise for date
    * id repeat count date
    * */
    fun getAllExercise() = viewModelScope.launch {
        allExercise.postValue(repository.getAllExercise())
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

    fun reset() {
        Log.d("#reset", "reset $state")
        state = 0
        sum = 0
        exercisePlan.postValue(ObjectAndRepeat(listOfExercise[state], state))
        repeat.postValue(state)
    }

    fun saveRepeat(saveInterrupted: SaveInterrupted) {
        if (state != 0) saveInterrupted.saveState(state)
    }

    fun deleteExerciseByDate(date: Long) = viewModelScope.launch {
        repository.deleteExerciseByDate(date)
        getAllExercise()
        getSumRepeat()
    }

}