package com.vad.pullup.ui

import android.widget.TextView

class IndicatorState(
    private val state1: TextView,
    private val state2: TextView,
    private val state3: TextView,
    private val state4: TextView,
    private val state5: TextView
    ) {

    fun setStates(listState: List<Int>) {
        state1.text = "${listState[0]}"
        state2.text = "${listState[1]}"
        state3.text = "${listState[2]}"
        state4.text = "${listState[3]}"
        state5.text = "${listState[4]}"
    }

}