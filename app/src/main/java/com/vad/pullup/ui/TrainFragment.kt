package com.vad.pullup.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import com.vad.pullup.R
import com.vad.pullup.data.BaseFragment

class TrainFragment : BaseFragment() {

    private lateinit var progressBar: ProgressBar
    private lateinit var buttonDone: Button
    private lateinit var textViewCount: TextView
    private lateinit var imageButtonAdd: ImageButton
    private lateinit var imageButtonRemove: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_train, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        progressBar = view.findViewById(R.id.progressBar) as ProgressBar
        buttonDone = view.findViewById(R.id.done) as Button
        textViewCount = view.findViewById(R.id.time) as TextView
        imageButtonAdd = view.findViewById(R.id.increase) as ImageButton
        imageButtonRemove = view.findViewById(R.id.decrease) as ImageButton

        exerciseViewModel.countOfRepeat.observe(viewLifecycleOwner) {
            textViewCount.text = "$it"
        }

        exerciseViewModel.getExerciseByDay(configuration.getDay())

        imageButtonAdd.setOnClickListener {
            exerciseViewModel.increaseCount(textViewCount.text.toString().toInt())
        }

        imageButtonRemove.setOnClickListener {
            exerciseViewModel.decreaseCount(textViewCount.text.toString().toInt())
        }

        buttonDone.setOnClickListener {
            exerciseViewModel.saveCount()
        }

    }

}