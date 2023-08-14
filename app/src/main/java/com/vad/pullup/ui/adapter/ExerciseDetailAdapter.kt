package com.vad.pullup.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vad.pullup.R
import com.vad.pullup.domain.model.entity.Exercise

class ExerciseDetailAdapter(private val exercises: List<Exercise>) : RecyclerView.Adapter<ExerciseDetailAdapter.ExerciseDetailVH>() {

    class ExerciseDetailVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val repeat = itemView.findViewById<TextView>(R.id.repeatTextView)
        private val count = itemView.findViewById<TextView>(R.id.countTextView)

        fun bind(r: String, c: String) {
            repeat.text = r
            count.text = c
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseDetailVH =
        ExerciseDetailVH(LayoutInflater.from(parent.context).inflate(R.layout.item_repeat_and_count, parent, false))

    override fun getItemCount(): Int = exercises.size

    override fun onBindViewHolder(holder: ExerciseDetailVH, position: Int) {
        holder.bind(exercises[position].repeat.toString(), exercises[position].count.toString())
    }
}