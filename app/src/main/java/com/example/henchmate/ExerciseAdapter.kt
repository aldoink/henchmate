package com.example.henchmate

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ExerciseAdapter(val context: Context, val exercises: List<Exercise>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val exerciseView: View
        val holder: ViewHolder

        if (convertView == null) {
            exerciseView = LayoutInflater.from(context).inflate(R.layout.exercise_card, null)
            holder = ViewHolder()
            holder.exerciseName = exerciseView.findViewById(R.id.exerciseHeader)
            holder.setRepInfo = exerciseView.findViewById(R.id.setRepInfo)
            exerciseView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
            exerciseView = convertView
        }
        val exercise = exercises[position]
        holder.exerciseName?.text = exercise.exerciseName
        holder.setRepInfo?.text = "${exercise.numberOfSets}x${exercise.repsPerSet} at ${exercise.weight}kg"

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

    private class ViewHolder(var exerciseName: TextView? = null, var setRepInfo: TextView? = null)
}