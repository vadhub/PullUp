package com.vad.pullup.data

import android.content.Context
import android.content.SharedPreferences

class SaveInterrupted(private val context: Context) {
    private lateinit var pref: SharedPreferences

    fun saveState(state: Int) {
        pref = context.getSharedPreferences("pull_ups", Context.MODE_PRIVATE)
        val ed: SharedPreferences.Editor = pref.edit()
        ed.putInt("state", state)
        ed.apply()
    }

    fun getState(): Int {
        pref = context.getSharedPreferences("pull_ups", Context.MODE_PRIVATE)
        return pref.getInt("state", -1)
    }
}