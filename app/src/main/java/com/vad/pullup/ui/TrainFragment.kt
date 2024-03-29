package com.vad.pullup.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.format.DateUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.vad.pullup.BaseFragment
import com.vad.pullup.MainActivity
import com.vad.pullup.R
import com.vad.pullup.data.SaveInterrupted
import com.vad.pullup.domain.model.AlarmHandler
import com.vad.pullup.domain.model.GlobalProgressHandle
import com.vad.pullup.domain.model.Timer
import com.vad.pullup.domain.model.TimerHandler
import com.vad.pullup.domain.model.entity.Exercise
import com.vad.pullup.domain.model.entity.TotalResult
import java.sql.Date
import java.util.concurrent.TimeUnit

class TrainFragment : BaseFragment(), TimerHandler {

    private lateinit var progressBar: CircularProgressIndicator
    private lateinit var buttonDone: Button
    private lateinit var textViewCount: TextView
    private lateinit var imageButtonAdd: ImageButton
    private lateinit var imageButtonRemove: ImageButton
    private var alarmHandler: AlarmHandler? = null
    private var timeoutChange = false
    private var finish = false
    private var progressMax = 120_000L
    private var timer: Timer = Timer(progressMax)
    private val saveInterrupted: SaveInterrupted by lazy { SaveInterrupted(thisContext) }
    private var isShowedDialog = false
    private var isSkip = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_train, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModelUIConfig.visibleNavigationBar(true)

        alarmHandler = AlarmHandler(thisContext)

        Log.d("#firstStartFragment", "d111ffff $finish")

        val globalProgressHandle = GlobalProgressHandle(configuration)

        getExercise()

        Log.d("#44", "onViewCreated ${(requireActivity() as MainActivity).exerciseViewModel}")

        if (savedInstanceState != null) {
            Log.d("#onViewCreated", "saveinstance $progressMax")
            timeoutChange = savedInstanceState.getBoolean("timeoutChange")
            finish = savedInstanceState.getBoolean("finish")
            progressMax = savedInstanceState.getLong("progressMax")
            Log.d("progress", "$progressMax")
            if (savedInstanceState.getBoolean("isStart")) {
                Log.d("#onViewCreated", "isStart")
                exerciseViewModel.startTimer(savedInstanceState.getLong("time"), this)
            }
        } else {
            if (saveInterrupted.getState() != -1 && !isShowedDialog) {
                val dialogContinue = DialogContinue()
                dialogContinue.onCreateDialog(thisContext,
                    {
                        exerciseViewModel.setState(saveInterrupted.getState())
                        isShowedDialog = true
                    }) {
                    isShowedDialog = true
                    saveInterrupted.saveState(-1)
                }.show()
            }
        }

        progressBar = view.findViewById(R.id.progressBar) as CircularProgressIndicator
        progressBar.scaleX = -1f

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

        val day = view.findViewById(R.id.status) as TextView
        val week = view.findViewById<TextView>(R.id.week)

        setDayAndWeek(day, week)

        var exercise = Exercise(0, 0, 0, Date(0))
        val indicator = IndicatorState(
            stateFirst,
            stateSecond,
            stateThird,
            stateFourth,
            stateFifth,
            thisContext
        )

        exerciseViewModel.countOfRepeat.observe(viewLifecycleOwner) {
            Log.d("#countOfRepeat", "countOfRepeat")
            textViewCount.text = "${it.first}"
            indicator.setStateToTextView(it.first, it.repeat + 1)
            exercise = Exercise(0, it.repeat + 1, it.first, exercise.date)
        }

        exerciseViewModel.exercisePlan.observe(viewLifecycleOwner) {
            Log.d("#exercisePlan", "exercisePlan")
            textViewCount.text = "${it.first.count}"
            exercise = Exercise(0, it.repeat + 1, it.first.count, Date(System.currentTimeMillis()))
        }

        exerciseViewModel.listCountByWeek.observe(viewLifecycleOwner) {
            Log.d("##list count", it.toTypedArray().contentToString())
            indicator.setStates(it)
        }

        exerciseViewModel.changeTimeout.observe(viewLifecycleOwner) {
            Log.d("#changeTimeout", "changeTimeout")
            updateButton(timeoutChange)
        }

        exerciseViewModel.timer.observe(viewLifecycleOwner) {
            Log.d("#timer", "msg")
            Log.d("#timer", "$it")
            timer = it
        }

        exerciseViewModel.repeat.observe(viewLifecycleOwner) {
            Log.d("s#tateLiveData", "stateLiveData $it")
            indicator.setIndicateRest(firstRest, secondRest, thirdRest, fourthRest, it)
        }

        exerciseViewModel.finish.observe(viewLifecycleOwner) {

            Log.d("#finish", "$it 1 $finish")

            if (!finish) {
                indicator.reset(firstRest, secondRest, thirdRest, fourthRest)
                createFinishDialog(it)
                configuration.saveDay(configuration.getDay() + 1)
                globalProgressHandle.handle()
                setDayAndWeek(day, week)
                finish = true
                timeoutChange = false
                saveInterrupted.saveState(-1)
            }
        }

        imageButtonAdd.setOnClickListener {
            if (!timeoutChange) {
                exerciseViewModel.increaseCount(textViewCount.text.toString().toInt())
            } else {
                progressMax += 10_000
                exerciseViewModel.setTimer(true, this)
                setMaxProgressBar(progressMax.toInt())
            }
        }

        imageButtonRemove.setOnClickListener {
            if (!timeoutChange) {
                exerciseViewModel.decreaseCount(textViewCount.text.toString().toInt())
            } else {
                if (progressMax >= 12_000) {
                    progressMax -= 10_000
                    exerciseViewModel.setTimer(false, this)
                    setMaxProgressBar(progressMax.toInt())
                }
            }
        }

        buttonDone.setOnClickListener {
            finish = false
            Log.d("#timeoutChange", "$timeoutChange")
            if (timeoutChange) {
                exerciseViewModel.skipTimer()
                isSkip = true
                finishTime()
            } else {
                exerciseViewModel.saveCount(exercise, this)
                timeoutChange = true
            }
        }

    }

    private fun createFinishDialog(it: TotalResult) {
        val finishDialog = FinishDialog()
        val fragmentManager = parentFragmentManager
        val bundle = Bundle()
        bundle.putInt("sum_result", it.sum)
        bundle.putInt("min_result", it.min)
        bundle.putInt("max_result", it.max)
        finishDialog.arguments = bundle
        finishDialog.show(fragmentManager, "finish dialog")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("#destroy", "destroy")
        exerciseViewModel.skipTimer()
        alarmHandler?.stopAlarm()
        alarmHandler?.cancelAlarm()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("#save", "state $finish")

        if (timer.isStart) {
            outState.putLong("time", timer.timeStartFrom)
        } else {
            outState.putLong("time", 120_000)
        }

        outState.putBoolean("isStart", timer.isStart)
        outState.putBoolean("timeoutChange", timeoutChange)
        outState.putLong("progressMax", progressMax)
        outState.putBoolean("finish", finish)
        exerciseViewModel.saveRepeat(saveInterrupted)
    }

    override fun showTime(time: Long) {
        val seconds = TimeUnit.MILLISECONDS.toSeconds(time)
        textViewCount.text = DateUtils.formatElapsedTime(seconds);
        progressBar.progress = seconds.toInt()
    }

    private fun setMaxProgressBar(time: Int) {
        progressBar.max = time / 1000
    }

    override fun finishTime() {
        playAlarm(isSkip)
        progressMax = 120_000
        exerciseViewModel.switchState()
        buttonDone.text = resources.getString(R.string.done)
        timeoutChange = false
        Log.d("finishTime", "$progressMax")
        setMaxProgressBar(progressMax.toInt())
        progressBar.progress = progressMax.toInt()
        isSkip = false
    }

    private fun playAlarm(isSkip: Boolean) {
        if (!isSkip) {
            alarmHandler?.playAlarm()
        }
    }

    private fun getExercise() {
        val week = configuration.getWeek()
        exerciseViewModel.getListOfCountByWeekPlan(week)
    }

    private fun updateButton(change: Boolean) {
        if (change) {
            buttonDone.text = resources.getString(R.string.skip)
        } else {
            buttonDone.text = resources.getString(R.string.done)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setDayAndWeek(dayTextView: TextView, weekTextView: TextView) {
        dayTextView.text = resources.getString(R.string.day) + " " + configuration.getDay()
        weekTextView.text = resources.getString(R.string.week) + " " + configuration.getWeek()
    }

}