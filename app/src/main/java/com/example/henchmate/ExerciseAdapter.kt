package com.example.henchmate

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v4.content.ContextCompat.getDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import com.example.henchmate.R.drawable.circle_button
import kotlin.math.roundToInt

class ExerciseAdapter(private val context: Context, private val exercises: List<Exercise>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val exerciseView: View
        val holder: ViewHolder
        val exercise = exercises[position]

        if (convertView == null) {
            exerciseView = LayoutInflater.from(context).inflate(R.layout.exercise_card, parent, false)
            holder = ViewHolder()
            holder.exerciseName = exerciseView.findViewById(R.id.exerciseHeader)
            holder.setRepInfo = exerciseView.findViewById(R.id.setRepInfo)
            holder.firstSetButton = exerciseView.findViewById(R.id.firstSet)
            holder.firstSetButton!!.text = exercise.setHistory[0]
            holder.firstSetButton!!.setOnClickListener { onSetButtonClicked(0, exercise, holder.firstSetButton!!) }
            holder.layout = exerciseView.findViewById(R.id.exerciseCardLayout)
            exerciseView.id = View.generateViewId()
            exerciseView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
            exerciseView = convertView
        }

        holder.exerciseName?.text = exercise.exerciseName
        holder.setRepInfo?.text = "${exercise.numberOfSets}x${exercise.repsPerSet} at ${exercise.weight}kg"
        holder.buttons.add(holder.firstSetButton!!)

        for (i in 1 until exercise.numberOfSets) {
            val setButton = Button(context)
            setButton.id = View.generateViewId()
            setButton.background = getDrawable(context, circle_button)
            setButton.layoutParams = ConstraintLayout.LayoutParams(45.dp(), 45.dp())
            setButton.setOnClickListener { onSetButtonClicked(setNumber = i, exercise = exercise, button = setButton) }
            holder.layout?.addView(setButton)
            holder.buttons.add(setButton)
            val set = ConstraintSet()
            set.clone(holder.layout!!)
            set.connect(setButton.id, ConstraintSet.LEFT, holder.buttons[i - 1].id, ConstraintSet.RIGHT, 24.dp())
            set.connect(setButton.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 24.dp())
            set.applyTo(holder.layout)
        }

        return exerciseView
    }

    override fun getItem(position: Int): Any {
        return exercises[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return exercises.count()
    }

    private class ViewHolder(
        var exerciseName: TextView? = null,
        var setRepInfo: TextView? = null,
        var firstSetButton: Button? = null,
        var layout: ConstraintLayout? = null,
        var buttons: ArrayList<Button> = ArrayList()
    )

    private fun onSetButtonClicked(setNumber: Int, exercise: Exercise, button: Button) {
        when {
            exercise.setHistory[setNumber] == "" -> exercise.setHistory[setNumber] = exercise.repsPerSet.toString()
            exercise.setHistory[setNumber] == "0" -> exercise.setHistory[setNumber] = ""
            else -> exercise.setHistory[setNumber] = exercise.setHistory[setNumber].toInt().minus(1).toString()
        }
        button.text = exercise.setHistory[setNumber]
    }

    private fun Int.dp(): Int {
        return (this * (context.resources.displayMetrics.density)).roundToInt()
    }

}
