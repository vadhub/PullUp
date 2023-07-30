package com.vad.pullup.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vad.pullup.R
import com.vad.pullup.data.ConverterProgram
import com.vad.pullup.data.ProgramItem
import com.vad.pullup.data.db.ExercisePlan

class ProgramAdapter(listExercisePlan: List<ExercisePlan>) : RecyclerView.Adapter<ProgramAdapter.ProgramViewHolder>() {

    private val listProgram = ConverterProgram.convertToListProgram(listExercisePlan, 30)

    class ProgramViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val weekTextView = itemView.findViewById(R.id.weekTextView) as TextView

        private val first = itemView.findViewById(R.id.countFirstTextView) as TextView
        private val second = itemView.findViewById(R.id.countSecondTextView) as TextView
        private val third = itemView.findViewById(R.id.countThirdTextView) as TextView
        private val fourth = itemView.findViewById(R.id.countFourthTextView) as TextView
        private val fifth = itemView.findViewById(R.id.countFifthTextView) as TextView

        fun bind(programItem: ProgramItem) {
            weekTextView.text = "${programItem.week}"

            first.text = "${programItem.first}"
            second.text = "${programItem.second}"
            third.text = "${programItem.third}"
            fourth.text = "${programItem.fourth}"
            fifth.text = "${programItem.fifth}"
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgramViewHolder =
        ProgramViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_program, parent, false))

    override fun onBindViewHolder(holder: ProgramViewHolder, position: Int) {
        holder.bind(listProgram[position])
    }

    override fun getItemCount(): Int = listProgram.size

}