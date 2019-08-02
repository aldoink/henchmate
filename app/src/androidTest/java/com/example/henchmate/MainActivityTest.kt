package com.example.henchmate

import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.core.internal.deps.guava.collect.Iterables
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.espresso.util.TreeIterables
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
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
class MainActivityTest {
    @Rule
    @JvmField
    val rule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

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
            .check(matches(withViewCount(withTagValue(`is`("Set Button")), 5)))
        onData(withExerciseName("Bench Press"))
            .check(matches(withViewCount(withTagValue(`is`("Set Button")), 3)))
        onData(withExerciseName("Bent Over Rows"))
            .check(matches(withViewCount(withTagValue(`is`("Set Button")), 1)))
    }

    private fun withExerciseName(name: String): Matcher<Exercise> {
        return object : TypeSafeMatcher<Exercise>() {
            override fun describeTo(description: Description?) {
                description?.appendText("Exercise name should be $name")
            }

            override fun matchesSafely(item: Exercise?): Boolean {
                return item?.exerciseName == name
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
                actualCount = Iterables.filter(iterable) { view -> viewMatcher.matches(view) }.count()
                return actualCount == expectedCount
            }
        }
    }
}
