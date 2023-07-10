package com.vad.pullup

import android.app.Application
import com.vad.pullup.data.db.AppDatabase

class App : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
}