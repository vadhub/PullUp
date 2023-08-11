package com.vad.pullup.ui

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.vad.pullup.R

class WarningDialog : DialogFragment() {

    private var dismissListener: DialogInterface.OnDismissListener? = null

    fun setOnDismissListener(dismissListener: DialogInterface.OnDismissListener) {
        this.dismissListener = dismissListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.warning_dialog_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val button = view.findViewById(R.id.buttonWarning) as Button

        button.setOnClickListener {
            dismiss()
        }


    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        dismissListener?.onDismiss(dialog)
    }

}