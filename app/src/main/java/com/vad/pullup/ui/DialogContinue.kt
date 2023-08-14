package com.vad.pullup.ui

import android.R
import android.content.Context
import androidx.appcompat.app.AlertDialog

class DialogContinue() {
    fun onCreateDialog(context: Context, continueTraining: Runnable, noContinue: Runnable): AlertDialog.Builder {
        val adb: AlertDialog.Builder = AlertDialog.Builder(context)
        // заголовок
        adb.setTitle("Continue?")
        // сообщение
        adb.setMessage("Continue workout?")
        // иконка
        adb.setIcon(R.drawable.ic_dialog_info)
        // кнопка положительного ответа
        adb.setPositiveButton(R.string.ok) { dialog, id ->
            continueTraining.run()
            dialog.cancel()
        }
        // кнопка отрицательного ответа
        adb.setNegativeButton(R.string.cancel) { dialog, id ->
            noContinue.run()
            dialog.cancel()
        }

        // кнопка нейтрального ответа
//            adb.setNeutralButton(R.string.cancel, myClickListener)
        // создаем диалог
        return adb
    }
}