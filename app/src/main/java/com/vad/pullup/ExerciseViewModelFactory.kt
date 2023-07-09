package com.vad.pullup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vad.pullup.data.ExerciseRepository

class ExerciseViewModelFactory(private val repository: ExerciseRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ExerciseViewModel(repository) as T
    }
}