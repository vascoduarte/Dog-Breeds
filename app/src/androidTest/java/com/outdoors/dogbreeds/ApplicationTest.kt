package com.outdoors.dogbreeds

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.outdoors.dogbreeds.main.MainActivity
import com.outdoors.dogbreeds.util.BreedListAdapter
import com.outdoors.dogbreeds.util.ImageListAdapter
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test


@HiltAndroidTest
class ApplicationTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Test
    fun runApp() {
        ActivityScenario.launch(MainActivity::class.java)

        sleep(3000)
        onView(withId(R.id.breedList_view)).check(matches(isDisplayed()))
        onView(withId(R.id.breedList_view))
            .perform(RecyclerViewActions.actionOnItemAtPosition<BreedListAdapter.ViewHolder>(4, click()))
        sleep(1000)

        onView(withId(R.id.breedImage_view))
            .perform(RecyclerViewActions.actionOnItemAtPosition<ImageListAdapter.ViewHolder>(0, click()))
        sleep(1000)

        pressBack()
        sleep(1000)

        onView(withId(R.id.breedImage_view))
            .perform(RecyclerViewActions.actionOnItemAtPosition<ImageListAdapter.ViewHolder>(0, click()))
        sleep(1000)

        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click());
        sleep(1000)

        onView(withId(R.id.aboutFragment)).perform(click())

        sleep(2000)
    }

    private fun sleep(time: Long) {
        try {
            Thread.sleep(time)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

    }
}