package com.vad.pullup

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.vad.pullup.data.Configuration
import com.vad.pullup.data.ExerciseRepository

open class BaseFragment : Fragment() {

    protected lateinit var configuration: Configuration
    protected lateinit var exerciseViewModel: ExerciseViewModel
    protected lateinit var thisContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        thisContext = context
        configuration = Configuration(context)
        val factory = ExerciseViewModelFactory(ExerciseRepository((requireActivity().application as App).database.exerciseDao()))
        exerciseViewModel = ViewModelProvider(this, factory).get(ExerciseViewModel::class.java)
    }

}