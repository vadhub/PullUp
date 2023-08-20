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
import com.vad.pullup.domain.model.entity.RepeatSum
import com.vad.pullup.domain.model.entity.TotalResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExerciseViewModel(private val repository: ExerciseRepository) : ViewModel() {

    private var timerHandle: Timer? = null

    /* state is progress exercise or repeat
    * example:
    * state count
    *   1    34
    *   2    34
    *  ...  ...
    *
    * initial zero because use in list of exercise plan
    * */
    private var state = 0

    // 34 23 20 23 12
    private var listOfCountByWeek: List<Int> = listOf()

    /* id  count week
    *   1   32    5
    *   2   34    5
    *   3   20    5
    *  ... ...   ...
    * */
    private var listOfPlan: List<ExercisePlan> = listOf()

    private var sum = 0
    private var max = 0
    private var min = 100

    val countOfRepeat: MutableLiveData<ObjectAndRepeat<Int>> = MutableLiveData()
    val exercisePlan: MutableLiveData<ObjectAndRepeat<ExercisePlan>> = MutableLiveData()
    val listCountByWeek: MutableLiveData<List<Int>> = MutableLiveData()
    val changeTimeout: MutableLiveData<Boolean> = MutableLiveData()
    val timer: MutableLiveData<Timer> = MutableLiveData()
    val repeat: MutableLiveData<Int> = MutableLiveData()
    val finish: MutableLiveData<TotalResult> = MutableLiveData()
    val sumRepeat: MutableLiveData<List<RepeatSum>> = MutableLiveData()
    val allProgramItem: MutableLiveData<List<ProgramItem>> = MutableLiveData()
    val allExercise: MutableLiveData<List<Exercise>> = MutableLiveData()

    fun setState(state: Int) {
        this.state = state
        repeat.postValue(state)
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

        if (listOfCountByWeek.size - 1 > state) {
            changeTimeout.postValue(true)
            startTimer(120_000, timerHandler)
        } else {
            reset()
            finish.postValue(TotalResult(max, min, sum))
        }
    }

    fun switchState() {
        Log.d("#switchState", "switchState")
        if (listOfCountByWeek.size - 1 > state) {
            state++
        }
        exercisePlan.postValue(ObjectAndRepeat(listOfPlan[state], state))
        repeat.postValue(state)
    }

    /* get list of count from plan program by week
    * example:
    * current week 5
    * getListOfCountByWeekPlan(5) -> (from database) get program plan on 5 week
    *
    * count week
    *   10   5     |
    *   6    5     |
    *   6    5      } <- is plan exercise
    *   5    5     |
    *   4    5     |
    *
    *  above -> get only count
    *
    * exercisePlan give ObjectAndRepeat(current count of plan program, repeat)
     */
    fun getListOfCountByWeekPlan(week: Int) = viewModelScope.launch {

        listOfPlan = repository.getPlanOfWeek(week)
        listOfCountByWeek = listOfPlan.map { it.count }

        listCountByWeek.postValue(listOfCountByWeek)
        exercisePlan.postValue(ObjectAndRepeat(listOfPlan[state], state))
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
        allProgramItem.postValue(repository.getAllItemProgram())
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
        exercisePlan.postValue(ObjectAndRepeat(listOfPlan[state], state))
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