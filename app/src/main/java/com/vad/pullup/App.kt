package com.vad.pullup

import android.app.Application
import android.app.NotificationManager
import android.util.Log
import com.vad.pullup.data.db.AppDatabase
import com.yandex.mobile.ads.common.MobileAds


class App : Application() {

    private val YANDEX_MOBILE_ADS_TAG = "YandexMobileAds"

    val database by lazy { AppDatabase.getDatabase(this) }

    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(
            this
        ) { Log.d(YANDEX_MOBILE_ADS_TAG, "SDK initialized") }
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
    }
}