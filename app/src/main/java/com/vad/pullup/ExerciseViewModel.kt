package com.vad.pullup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vad.pullup.data.db.Exercise
import com.vad.pullup.data.db.ExercisePlan
import com.vad.pullup.data.ExerciseRepository
import kotlinx.coroutines.launch

class ExerciseViewModel(private val repository: ExerciseRepository) : ViewModel() {


    var countOfRepeat: MutableLiveData<Int> = MutableLiveData()
    var exercisePlan: MutableLiveData<ExercisePlan> = MutableLiveData()

    fun increaseCount(count: Int) = countOfRepeat.postValue(count+1)

    fun decreaseCount(count: Int) {
        if (count > 0) countOfRepeat.postValue(count-1)
    }

    fun saveCount(exercise: Exercise) = viewModelScope.launch {
        repository.writeExercise(exercise)
    }

    fun getExerciseByDay(day: Int) = viewModelScope.launch {
        exercisePlan.postValue(repository.getPlanOfDay(day))
    }
}