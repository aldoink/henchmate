package com.example.henchmate

import android.view.View
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.core.internal.deps.guava.collect.Iterables
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.util.TreeIterables
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class WorkoutActivityTest {
    @Rule
    @JvmField
    val rule: ActivityTestRule<WorkoutActivity> = ActivityTestRule(WorkoutActivity::class.java)

    @Test
    fun displays_correct_set_rep_info() {
        onData(withExerciseName("Squats"))
            .onChildView(withId(R.id.setRepInfo))
            .check(matches(withText("5x5 at 70kg")))
        onData(withExerciseName("Bench Press"))
            .onChildView(withId(R.id.setRepInfo))
            .check(matches(withText("3x5 at 45.5kg")))
        onData(withExerciseName("Bent Over Rows"))
            .onChildView(withId(R.id.setRepInfo))
            .check(matches(withText("1x5 at 45.5kg")))
    }

    @Test
    fun has_correct_number_of_set_buttons() {
        onData(withExerciseName("Squats"))
            .check(matches(withViewCount(withTagContainingText("Set Button"), 5)))
        onData(withExerciseName("Bench Press"))
            .check(matches(withViewCount(withTagContainingText("Set Button"), 3)))
        onData(withExerciseName("Bent Over Rows"))
            .check(matches(withViewCount(withTagContainingText("Set Button"), 1)))
    }

    @Test
    fun clicking_set_button_adjusts_rep_count() {
        onData(withExerciseName("Bench Press"))
            .onChildView(withId(R.id.firstSet))
            .perform(click())
            .check(matches(withText("5")))
            .perform(click())
            .check(matches(withText("4")))
            .perform(click())
            .check(matches(withText("3")))
            .perform(click())
            .check(matches(withText("2")))
            .perform(click())
            .check(matches(withText("1")))
            .perform(click())
            .check(matches(withText("0")))
            .perform(click())
            .check(matches(withText("")))
            .perform(click())
            .check(matches(withText("5")))
    }

    private fun withExerciseName(name: String): Matcher<Exercise> {
        return object : TypeSafeMatcher<Exercise>() {
            override fun describeTo(description: Description?) {
                description?.appendText("Exercise name should be $name")
            }

            override fun matchesSafely(item: Exercise?): Boolean {
                return item?.name == name
            }

        }
    }

    private fun withTagContainingText(text: String): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("View should have a tag containing text: '${text}'")
            }

            override fun matchesSafely(item: View): Boolean {
                return if (item.tag is String) {
                    val tag = item.tag as String
                    tag.contains(text)
                } else {
                    false
                }
            }
        }
    }


    private fun withViewCount(viewMatcher: Matcher<View>, expectedCount: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            var actualCount = -1

            override fun describeTo(description: Description) {
                if (actualCount >= 0) {
                    description.appendText("With expected number of items: $expectedCount")
                    description.appendText("\n With matcher: ")
                    viewMatcher.describeTo(description)
                    description.appendText("\n But got: $actualCount")
                }
            }

            override fun matchesSafely(root: View): Boolean {
                actualCount = 0
                val iterable = TreeIterables.breadthFirstViewTraversal(root)
                actualCount =
                    Iterables.filter(iterable) { view -> viewMatcher.matches(view) }.count()
                return actualCount == expectedCount
            }
        }
    }
}
