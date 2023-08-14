package com.vad.pullup.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vad.pullup.R
import com.vad.pullup.domain.model.entity.Exercise


class ExerciseAdapter(
    private val listDateAndExercise: List<Pair<String, List<Exercise>>>,
    private val itemOnClickListener: ItemOnClickListenerView
) : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dateTextView = itemView.findViewById(R.id.dateTextView) as TextView
        private val recyclerView =
            itemView.findViewById(R.id.repeatAndCountRecyclerView) as RecyclerView
        val moreOptions = itemView.findViewById(R.id.more_options) as ImageButton

        @SuppressLint("SimpleDateFormat")
        fun onBind(date: String, listExercise: List<Exercise>) {
            dateTextView.text = date
            val adapter = ExerciseDetailAdapter(listExercise)
            recyclerView.layoutManager = LinearLayoutManager(itemView.context)
            recyclerView.adapter = adapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder =
        ExerciseViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        )

    override fun getItemCount(): Int = listDateAndExercise.size

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.onBind(listDateAndExercise[position].first, listDateAndExercise[position].second)
        holder.moreOptions.setOnClickListener {
            itemOnClickListener.onClick(listDateAndExercise[position].second[0].date, it)
        }
    }
}