package com.example.henchmate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.workout_activity.*

class WorkoutActivity : AppCompatActivity() {

    private val squats =
        Exercise(exerciseName = "Squats", numberOfSets = 5, repsPerSet = 5, weight = 70)
    private val bench =
        Exercise(exerciseName = "Bench Press", numberOfSets = 3, repsPerSet = 5, weight = 45.5)
    private val bentOverRows =
        Exercise(exerciseName = "Bent Over Rows", numberOfSets = 1, repsPerSet = 5, weight = 45.5)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.workout_activity)
        exerciseList.adapter = ExerciseAdapter(
            this, listOf(
                squats,
                bench,
                bentOverRows
            )
        )
        val workoutButtons =
            layoutInflater.inflate(R.layout.workout_buttons, workout_activity, false)
        exerciseList.addFooterView(workoutButtons)
    }
}
