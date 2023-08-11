package com.vad.pullup.ui

import android.app.Dialog
import android.content.DialogInterface
import android.content.DialogInterface.OnClickListener
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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

                .setPositiveButton("Yes",
                    DialogInterface.OnClickListener { dialog, id ->
                        Log.d("Dialog" ,"yes")
                        onAcceptListener?.onAccept()
                    })
                .setNegativeButton("Cancel",
                    DialogInterface.OnClickListener { dialog, id ->
                        Log.d("Dialog" ,"cancel")
                        getDialog()?.cancel()
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}