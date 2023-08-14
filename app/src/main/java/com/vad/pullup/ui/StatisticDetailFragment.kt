package com.vad.pullup.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vad.pullup.BaseFragment
import com.vad.pullup.R
import com.vad.pullup.ui.adapter.ExerciseAdapter

class StatisticDetailFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_statistic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val emptyText = view.findViewById<TextView>(R.id.emptyTextView)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewDetail)
        recyclerView.layoutManager = LinearLayoutManager(thisContext)

        exerciseViewModel.getAllExercise()
        exerciseViewModel.allExercise.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                emptyText.visibility = View.GONE
            }
            val listExerciseByDate = it.groupBy { it.date.toString() }.toList()
            val adapter = ExerciseAdapter(listExerciseByDate)
            recyclerView.adapter = adapter
        }

    }

}