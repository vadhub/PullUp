package com.vad.pullup.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vad.pullup.BaseFragment
import com.vad.pullup.R
import com.vad.pullup.ui.adapter.ExerciseAdapter
import com.vad.pullup.ui.adapter.ItemOnClickListener
import com.vad.pullup.ui.adapter.ItemOnClickListenerView
import java.sql.Date

class StatisticDetailFragment : BaseFragment(), ItemOnClickListenerView {

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
            val adapter = ExerciseAdapter(listExerciseByDate, this)
            recyclerView.adapter = adapter
        }

    }

    override fun onClick(date: Date, view: View) {
        val popupMenu = PopupMenu(thisContext, view)
        popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { menuItem ->

            exerciseViewModel.deleteExerciseByDate(date.time)
            //todo
            Toast.makeText(thisContext, "You deleted " , Toast.LENGTH_SHORT).show()
            true
        }
        // Showing the popup menu
        popupMenu.show()
    }

}