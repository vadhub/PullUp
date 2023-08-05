package com.vad.pullup.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.vad.pullup.R
import com.vad.pullup.BaseFragment

class PreparationFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_preparation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repeat = view.findViewById(R.id.repeat) as TextView

        val increase = view.findViewById(R.id.increase) as ImageButton
        val decrease = view.findViewById(R.id.decrease) as ImageButton

        val buttonDone = view.findViewById(R.id.buttonDone) as Button

        increase.setOnClickListener {
            exerciseViewModel.increaseCount(repeat.text.toString().toInt())
        }

        decrease.setOnClickListener {
            exerciseViewModel.decreaseCount(repeat.text.toString().toInt())
        }

        exerciseViewModel.countOfRepeat.observe(viewLifecycleOwner) {
            repeat.text = "$it"
        }

        buttonDone.setOnClickListener {
            //2 version
            if (repeat.text.toString().toInt() < 10) {
                 configuration.saveDay(1)
            } else if (repeat.text.toString().toInt() in 11..14) {
                configuration.saveDay(7*2)
            } else if (repeat.text.toString().toInt() in 14..20) {
                configuration.saveDay(7*9)
            } else if (repeat.text.toString().toInt() in 20..23) {
                configuration.saveDay(7*15)
            } else if (repeat.text.toString().toInt() in 23..26) {
                configuration.saveDay(7*17)
            } else if (repeat.text.toString().toInt() > 30) {
                configuration.saveDay(7*29)
            }
            findNavController().navigate(R.id.trainFragment)
        }
    }


}