package com.vad.pullup.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.vad.pullup.R
import com.vad.pullup.data.BaseFragment
import com.vad.pullup.data.Timer
import com.vad.pullup.data.TimerHandler
import com.vad.pullup.data.db.Exercise
import java.sql.Date
import java.util.concurrent.TimeUnit

class TrainFragment : BaseFragment(), TimerHandler {

    private lateinit var progressBar: ProgressBar
    private lateinit var buttonDone: Button
    private lateinit var textViewCount: TextView
    private lateinit var imageButtonAdd: ImageButton
    private lateinit var imageButtonRemove: ImageButton
    private var timer: Timer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_train, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        progressBar = view.findViewById(R.id.progressBar) as ProgressBar
        buttonDone = view.findViewById(R.id.done) as Button
        textViewCount = view.findViewById(R.id.time) as TextView
        imageButtonAdd = view.findViewById(R.id.increase) as ImageButton
        imageButtonRemove = view.findViewById(R.id.decrease) as ImageButton

        val stateFirst = view.findViewById(R.id.firstTask) as TextView
        val stateSecond = view.findViewById(R.id.secondTask) as TextView
        val stateThird = view.findViewById(R.id.thirdTask) as TextView
        val stateFourth = view.findViewById(R.id.fourthTask) as TextView
        val stateFifth = view.findViewById(R.id.fifthTask) as TextView

        val firstRest = view.findViewById(R.id.firstFree) as ImageView
        val secondRest = view.findViewById(R.id.secondFree) as ImageView
        val thirdRest = view.findViewById(R.id.thirdFree) as ImageView
        val fourthRest = view.findViewById(R.id.fourthFree) as ImageView

        var exercise = Exercise(0, 0, 0, Date(0))
        val indicator = IndicatorState(stateFirst, stateSecond, stateThird, stateFourth, stateFifth, thisContext)

        exerciseViewModel.getExerciseByDay(configuration.getDay())
        exerciseViewModel.getListOfCountExercise(configuration.getDay())

        exerciseViewModel.countOfRepeat.observe(viewLifecycleOwner) {
            textViewCount.text = "$it"
            exercise = Exercise(0, exercise.state, it, exercise.date)
        }

        exerciseViewModel.exercisePlan.observe(viewLifecycleOwner) {
            textViewCount.text = "${it.count}"
            exercise = Exercise(0, it.state, it.count, Date(System.currentTimeMillis()))
        }

        exerciseViewModel.listCount.observe(viewLifecycleOwner) {
            Log.d("##1", it.toTypedArray().contentToString())
            indicator.setStates(it)
        }

        exerciseViewModel.changeTimeout.observe(viewLifecycleOwner) {
            if (it) {
                buttonDone.text = "finish"
            } else {
                buttonDone.text = "done"
            }
        }

        exerciseViewModel.timer.observe(viewLifecycleOwner) {
            timer = null
            timer = it
            timer?.setTimerHandler(this)
            timer?.startTimer()
        }

        exerciseViewModel.stateLiveData.observe(viewLifecycleOwner) {
            indicator.setIndicateRest(firstRest, secondRest, thirdRest, fourthRest, it)
        }

        imageButtonAdd.setOnClickListener {
            exerciseViewModel.increaseCount(textViewCount.text.toString().toInt())
        }

        imageButtonRemove.setOnClickListener {
            exerciseViewModel.decreaseCount(textViewCount.text.toString().toInt())
        }

        buttonDone.setOnClickListener {
            exerciseViewModel.saveCount(exercise)
        }

    }

    override fun showTime(time: Long) {

        val mTime = String.format("%02d:%02d",
            TimeUnit.MILLISECONDS.toMinutes(time),
            TimeUnit.MILLISECONDS.toSeconds(time) -
                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time)))

        textViewCount.text = mTime
    }

    override fun finishTime() {
        exerciseViewModel.switchState()
        buttonDone.text = "done"
    }

}