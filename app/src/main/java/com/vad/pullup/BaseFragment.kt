package com.vad.pullup

import android.content.Context
import androidx.fragment.app.Fragment
import com.vad.pullup.data.Configuration
import com.vad.pullup.ui.viewmodel.ExerciseViewModel
import com.vad.pullup.ui.viewmodel.ViewModelUIConfig

open class BaseFragment : Fragment() {

    protected lateinit var configuration: Configuration
    protected lateinit var exerciseViewModel: ExerciseViewModel
    protected lateinit var thisContext: Context
    protected lateinit var viewModelUIConfig: ViewModelUIConfig

    override fun onAttach(context: Context) {
        super.onAttach(context)
        thisContext = context
        configuration = Configuration(context)
        viewModelUIConfig = (requireActivity() as MainActivity).visibleNavBar
        exerciseViewModel = (requireActivity() as MainActivity).exerciseViewModel
    }
}