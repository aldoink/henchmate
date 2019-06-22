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
import android.widget.LinearLayout
import android.widget.TextView
import com.example.henchmate.R.drawable.circle_button
import kotlin.math.roundToInt

class ExerciseAdapter(private val context: Context, private val exercises: List<Exercise>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val exerciseView: View
        val holder: ViewHolder

        if (convertView == null) {
            exerciseView = LayoutInflater.from(context).inflate(R.layout.exercise_card, parent, false)
            holder = ViewHolder()
            holder.exerciseName = exerciseView.findViewById(R.id.exerciseHeader)
            holder.setRepInfo = exerciseView.findViewById(R.id.setRepInfo)
            holder.layout = exerciseView.findViewById(R.id.exerciseCard)
            exerciseView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
            exerciseView = convertView
        }
        val exercise = exercises[position]
        holder.exerciseName?.text = exercise.exerciseName
        holder.setRepInfo?.text = "${exercise.numberOfSets}x${exercise.repsPerSet} at ${exercise.weight}kg"

        val btnSet = Button(context)
        btnSet.id = View.generateViewId()
        btnSet.text = "5"
        btnSet.background = getDrawable(context, circle_button)
        val lp = LinearLayout.LayoutParams(45.dp(context), 45.dp(context))
        btnSet.layoutParams = lp
        holder.layout?.addView(btnSet)

        val constraints = ConstraintSet()
        constraints.clone(holder.layout)
        constraints.connect(btnSet.id, ConstraintSet.TOP, R.id.exerciseHeader, ConstraintSet.BOTTOM, 16.dp(context))
        constraints.connect(
            btnSet.id,
            ConstraintSet.START,
            ConstraintSet.PARENT_ID,
            ConstraintSet.START,
            16.dp(context)
        )
        constraints.connect(
            btnSet.id,
            ConstraintSet.BOTTOM,
            ConstraintSet.PARENT_ID,
            ConstraintSet.BOTTOM,
            16.dp(context)
        )

        constraints.applyTo(holder.layout)

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

    private fun Int.dp(context: Context): Int {
        return (this * context.resources.displayMetrics.density).roundToInt()
    }

    private class ViewHolder(
        var exerciseName: TextView? = null,
        var setRepInfo: TextView? = null,
        var layout: ConstraintLayout? = null
    )
}