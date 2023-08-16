package com.vad.pullup.ui

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.vad.pullup.R

class WarningDialog : DialogFragment() {

    private var onAcceptListener: OnAcceptListener? = null

    fun setOnAcceptListener(onAcceptListener: OnAcceptListener) {
        this.onAcceptListener = onAcceptListener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater;

            builder.setView(inflater.inflate(R.layout.warning_dialog_fragment, null))

                .setPositiveButton(
                    "Yes"
                ) { _, _ ->
                    Log.d("Dialog", "yes")
                    onAcceptListener?.onAccept()
                }
                .setNegativeButton(
                    "Cancel"
                ) { _, _ ->
                    Log.d("Dialog", "cancel")
                    dialog?.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}