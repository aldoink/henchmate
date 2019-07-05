package com.example.henchmate

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView

class ExerciseAdapter(val context: Context, val exercises: List<Exercise>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val exerciseView: View
        val holder: ViewHolder

        if (convertView == null) {
            exerciseView = LayoutInflater.from(context).inflate(R.layout.exercise_card, parent, false)
            holder = ViewHolder()
            holder.exerciseName = exerciseView.findViewById(R.id.exerciseHeader)
            holder.setRepInfo = exerciseView.findViewById(R.id.setRepInfo)
            holder.firstSetButton = exerciseView.findViewById(R.id.firstSet)
            holder.layout = exerciseView.findViewById(R.id.exerciseCardLayout)
            exerciseView.id = View.generateViewId()
            exerciseView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
            exerciseView = convertView
        }

        val exercise = exercises[position]
        holder.exerciseName?.text = exercise.exerciseName
        holder.setRepInfo?.text = "${exercise.numberOfSets}x${exercise.repsPerSet} at ${exercise.weight}kg"

        val setButton = Button(context)
        setButton.id = View.generateViewId()
        setButton.background = context.getDrawable(R.drawable.circle_button)
        setButton.layoutParams = ConstraintLayout.LayoutParams(45.dp(), 45.dp())
        holder.layout?.addView(setButton)
        val set = ConstraintSet()
        set.clone(holder.layout!!)
        set.connect(setButton.id, ConstraintSet.LEFT, holder.firstSetButton!!.id, ConstraintSet.RIGHT, 24.dp())
        set.connect(setButton.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 24.dp())
        set.applyTo(holder.layout)

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
        var layout: ConstraintLayout? = null
    )

    private fun Int.dp(): Int {
        return Math.round(this * (context.resources.displayMetrics.density))
    }
}