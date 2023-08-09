package com.vad.pullup.ui

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.vad.pullup.BaseFragment
import com.vad.pullup.R
import com.vad.pullup.data.entity.ProgramItem
import com.vad.pullup.ui.adapter.ItemOnClickListener
import com.vad.pullup.ui.adapter.ProgramAdapter

class ChooseProgramFragment : BaseFragment(), ItemOnClickListener {

    private lateinit var listOfItemProgram: List<ProgramItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choose_program, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.findViewById(R.id.recyclerProgram) as RecyclerView
        recyclerView.addItemDecoration(DividerItemDecoration(thisContext, DividerItemDecoration.VERTICAL))
        recyclerView.layoutManager = LinearLayoutManager(thisContext)

        exerciseViewModel.getAllProgram()
        exerciseViewModel.allProgram.observe(viewLifecycleOwner) {
            listOfItemProgram = it
            val adapter = ProgramAdapter(it, this)
            recyclerView.adapter = adapter
        }
    }

    override fun onClick(position: Int) {
        val week = listOfItemProgram[position].week
        val snackBarView = Snackbar.make(requireView(), "Set $week week", Snackbar.LENGTH_SHORT)
        val view = snackBarView.view
        val params = view.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.TOP
        view.layoutParams = params
        snackBarView.animationMode = BaseTransientBottomBar.ANIMATION_MODE_FADE
        snackBarView.show()
        Log.d("item", "$week")
        configuration.saveDay(week*7)
    }

}