package com.vad.pullup.ui

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.vad.pullup.R

class IndicatorState(
    private val state1: TextView,
    private val state2: TextView,
    private val state3: TextView,
    private val state4: TextView,
    private val state5: TextView,
    private val context: Context
) {

    fun setStates(listState: List<Int>) {
        state1.text = "${listState[0]}"
        state2.text = "${listState[1]}"
        state3.text = "${listState[2]}"
        state4.text = "${listState[3]}"
        state5.text = "${listState[4]}"
    }

    fun setIndicateRest(
        firstRest: ImageView,
        secondRest: ImageView,
        thirdRest: ImageView,
        fourthRest: ImageView,
        round: Int
    ) {

        when (round) {
            1 -> firstRest.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_radio_button_checked_24))
            2 -> {
                firstRest.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_radio_button_checked_24))
                secondRest.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_radio_button_checked_24))
            }

            3 -> {
                firstRest.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_radio_button_checked_24))
                secondRest.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_radio_button_checked_24))
                thirdRest.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_radio_button_checked_24))
            }

            4 -> {
                firstRest.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_radio_button_checked_24))
                secondRest.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_radio_button_checked_24))
                thirdRest.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_radio_button_checked_24))
                fourthRest.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_radio_button_checked_24))
            }
        }

    }

    fun reset(
        firstRest: ImageView,
        secondRest: ImageView,
        thirdRest: ImageView,
        fourthRest: ImageView
    ) {
        firstRest.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_radio_button_unchecked_24))
        secondRest.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_radio_button_unchecked_24))
        thirdRest.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_radio_button_unchecked_24))
        fourthRest.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_radio_button_unchecked_24))
    }

}