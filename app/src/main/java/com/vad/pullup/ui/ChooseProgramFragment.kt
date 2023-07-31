package com.vad.pullup.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vad.pullup.BaseFragment
import com.vad.pullup.R
import com.vad.pullup.ui.adapter.ProgramAdapter

class ChooseProgramFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choose_program, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.findViewById(R.id.recyclerProgram) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(thisContext)

        exerciseViewModel.getAllProgram()
        exerciseViewModel.allProgram.observe(viewLifecycleOwner) {
            val adapter = ProgramAdapter(it)
            recyclerView.adapter = adapter
        }
    }

}