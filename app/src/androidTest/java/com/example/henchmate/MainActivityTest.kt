package com.example.henchmate

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    @Rule
    @JvmField
    val mainActivityRule = IntentsTestRule(MainActivity::class.java)

    @Test
    fun launches_the_workout_activity_when_the_Workout_A_button_is_clicked() {
        onView(ViewMatchers.withId(R.id.btnWorkoutA))
            .perform(click())

        intended(hasComponent(WorkoutActivity::class.java.name))
    }

    @Test
    fun launches_the_workout_activity_when_the_Workout_B_button_is_clicked() {
        onView(ViewMatchers.withId(R.id.btnWorkoutB))
            .perform(click())

        intended(hasComponent(WorkoutActivity::class.java.name))
    }

}