package com.example.henchmate

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnWorkoutA.setOnClickListener(launchWorkoutActivity())
        btnWorkoutB.setOnClickListener(launchWorkoutActivity())
    }

    private fun launchWorkoutActivity(): (View) -> Unit {
        return {
            val intent = Intent(this, WorkoutActivity::class.java)
            startActivity(intent)
        }
    }

}
