package com.vad.pullup.data

import android.content.Context
import android.content.SharedPreferences

class Configuration(private val context: Context) {

    private lateinit var pref: SharedPreferences

    fun saveDay(day: Int) {
        pref = context.getSharedPreferences("pull_up_day", Context.MODE_PRIVATE)
        val ed: SharedPreferences.Editor = pref.edit()
        ed.putInt("day", day)
        ed.apply()
    }

    fun getDay(): Int {
        pref = context.getSharedPreferences("pull_up_day", Context.MODE_PRIVATE)
        return pref.getInt("day",1)
    }

    fun saveFirstStart(isFirst: Boolean) {
        pref = context.getSharedPreferences("com.vad.pullup.firstrun", Context.MODE_PRIVATE)
        val ed: SharedPreferences.Editor = pref.edit()
        ed.putBoolean("first_run", isFirst)
        ed.apply()
    }

    fun getFirstStart(): Boolean {
        pref = context.getSharedPreferences("com.vad.pullup.firstrun", Context.MODE_PRIVATE)
        return pref.getBoolean("first_run", true)
    }

}