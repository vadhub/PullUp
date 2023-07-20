package com.vad.pullup.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.vad.pullup.R

class FinishDialog : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.finish_dialog_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val result = view.findViewById(R.id.result) as TextView
        val button = view.findViewById(R.id.buttonOk) as Button

        result.text = "Your Result Sum: " + arguments?.getInt("sum")

        button.setOnClickListener { dismiss() }
    }
}