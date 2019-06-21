package com.example.henchmate

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val squats = Exercise(exerciseName = "Squats", numberOfSets = 5, repsPerSet = 5, weight = 70)
    val bench = Exercise(exerciseName = "Bench Press", numberOfSets = 5, repsPerSet = 5, weight = 45.5)
    val bentOverRows = Exercise(exerciseName = "Bent Over Rows", numberOfSets = 5, repsPerSet = 5, weight = 45.5)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        exerciseList.adapter = ExerciseAdapter(this, listOf(squats,bench,bentOverRows))

    }
}
