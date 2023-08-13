package com.vad.pullup.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vad.pullup.R
import com.vad.pullup.domain.model.entity.RepeatSum
import java.sql.Date
import java.text.SimpleDateFormat


class ExerciseAdapter(private val listRepeatSum: List<RepeatSum>) : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dateTextView = itemView.findViewById(R.id.dateTextView) as TextView
        private val recyclerView = itemView.findViewById(R.id.repeatAndCountRecyclerView) as RecyclerView

        @SuppressLint("SimpleDateFormat")
        fun onBind(date: Date) {
            val simpleDateFormat = SimpleDateFormat("MM.dd")
            dateTextView.text = simpleDateFormat.format(date)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder =
        ExerciseViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false))

    override fun getItemCount(): Int = listRepeatSum.size

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.onBind(listRepeatSum[position].dateRepeat)
    }
}