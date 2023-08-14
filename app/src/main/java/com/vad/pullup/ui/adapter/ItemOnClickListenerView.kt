package com.vad.pullup.ui.adapter

import android.view.View
import java.sql.Date

interface ItemOnClickListenerView {
    fun onClick(date: Date, view: View)
}