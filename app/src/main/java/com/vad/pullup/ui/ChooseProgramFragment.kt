package com.vad.pullup.ui

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.vad.pullup.BaseFragment
import com.vad.pullup.MainActivity
import com.vad.pullup.R
import com.vad.pullup.domain.model.entity.ProgramItem
import com.vad.pullup.ui.adapter.ItemOnClickListener
import com.vad.pullup.ui.adapter.ProgramAdapter

class ChooseProgramFragment : BaseFragment(), ItemOnClickListener, OnAcceptListener {

    private lateinit var listOfItemProgram: List<ProgramItem>
    private var week = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choose_program, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val progressBar = view.findViewById<ProgressBar>(R.id.loadingProgressBar)
        val recyclerView = view.findViewById(R.id.recyclerProgram) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

        exerciseViewModel.getAllProgram()
        exerciseViewModel.allProgram.observe(viewLifecycleOwner) {
            listOfItemProgram = it
            val adapter = ProgramAdapter(it, this)
            recyclerView.adapter = adapter
            Log.d("viewmodel", "recycler")
        }

        recyclerView.viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                progressBar.visibility = View.GONE
                Log.d("viewTree", "ok")
                recyclerView.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }

    override fun onClick(position: Int) {
        val warningDialog = WarningDialog()
        val fragmentManager = parentFragmentManager
        warningDialog.setOnAcceptListener(this)
        warningDialog.show(fragmentManager, "warning fragment")
        week = listOfItemProgram[position].week
    }

    override fun onAccept() {
        Log.d("onAccept", "accept")
        val snackBarView = Snackbar.make(requireView(), "Set $week week", Snackbar.LENGTH_SHORT)
        val view = snackBarView.view
        val params = view.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.TOP
        view.layoutParams = params
        snackBarView.animationMode = BaseTransientBottomBar.ANIMATION_MODE_FADE
        snackBarView.show()
        Log.d("item", "$week ${(requireActivity() as MainActivity).exerciseViewModel}")
        configuration.saveDay(week*7)
        exerciseViewModel.reset()
    }



}