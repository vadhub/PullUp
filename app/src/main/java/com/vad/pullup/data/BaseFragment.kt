package com.vad.pullup.data

import android.content.Context
import androidx.fragment.app.Fragment
import com.vad.pullup.ExerciseViewModel
import com.vad.pullup.MainActivity

open class BaseFragment : Fragment() {

    protected lateinit var configuration: Configuration
    protected lateinit var exerciseViewModel: ExerciseViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        configuration = (requireActivity() as MainActivity).configuration
        exerciseViewModel = (requireActivity() as MainActivity).exerciseViewModel
    }

}