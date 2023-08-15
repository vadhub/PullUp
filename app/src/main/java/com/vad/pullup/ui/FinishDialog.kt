package com.vad.pullup.ui

import android.annotation.SuppressLint
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

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sum = view.findViewById(R.id.sum) as TextView
        val max = view.findViewById(R.id.maxPerRepeat) as TextView
        val min = view.findViewById(R.id.minPerRepeat) as TextView
        val button = view.findViewById(R.id.buttonOk) as Button

        sum.text = resources.getString(R.string.result_sum)+" " + arguments?.getInt("sum")
        max.text = resources.getString(R.string.result_max)+" " + arguments?.getInt("max")
        min.text = resources.getString(R.string.result_min)+" " + arguments?.getInt("min")

        button.setOnClickListener {
            dismiss()
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        dismissListener?.onDismiss(dialog)
    }
}