package com.vad.pullup.ui

import android.R
import android.content.Context
import androidx.appcompat.app.AlertDialog

class DialogContinue {
    fun onCreateDialog(context: Context, continueTraining: Runnable, noContinue: Runnable): AlertDialog.Builder {
        val adb: AlertDialog.Builder = AlertDialog.Builder(context)

        adb.setTitle("Continue?")
        adb.setMessage("Continue workout?")
        adb.setIcon(R.drawable.ic_dialog_info)
        adb.setPositiveButton(R.string.ok) { dialog, id ->
            continueTraining.run()
            dialog.cancel()
        }
        adb.setNegativeButton(R.string.cancel) { dialog, id ->
            noContinue.run()
            dialog.cancel()
        }
        return adb
    }
}