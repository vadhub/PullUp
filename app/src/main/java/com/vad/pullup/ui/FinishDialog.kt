package com.vad.pullup.ui

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.vad.pullup.R

class FinishDialog : DialogFragment() {

    private var dismissListener: DialogInterface.OnDismissListener? = null

    fun setOnDismissListener(dismissListener: DialogInterface.OnDismissListener) {
        this.dismissListener = dismissListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.finish_dialog_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val result = view.findViewById(R.id.result) as TextView
        val button = view.findViewById(R.id.buttonOk) as Button

        result.text = resources.getString(R.string.result_sum)+" " + arguments?.getInt("sum")

        button.setOnClickListener {
            dismiss()
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        dismissListener?.onDismiss(dialog)
    }
}