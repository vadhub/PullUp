package com.vad.pullup.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
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

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sumTextView = view.findViewById(R.id.sum) as TextView
        val maxTextView = view.findViewById(R.id.maxPerRepeat) as TextView
        val minTextView = view.findViewById(R.id.minPerRepeat) as TextView
        val button = view.findViewById(R.id.buttonOk) as Button

        val sum = arguments?.getInt("sum_result")
        val max = arguments?.getInt("max_result")
        val min = arguments?.getInt("min_result")
        Log.d("#dialogFinish", "$sum $max $min")

        sumTextView.text = resources.getString(R.string.result_sum)+ " $sum"
        maxTextView.text = resources.getString(R.string.result_max)+ " $max"
        minTextView.text = resources.getString(R.string.result_min)+ " $min"

        button.setOnClickListener {
            dismiss()
        }
    }

}