package com.eb.mydictionaryApp.ui


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.eb.mydictionaryApp.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.*
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class addingWordSnackBarOpens {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SplashScreen::class.java)

    @Test
    fun addingWordSnackBarOpens() {
        val appCompatTextView = onData(anything())
            .inAdapterView(
                allOf(
                    withId(R.id.language_list_view),
                    childAtPosition(
                        withClassName(`is`("android.widget.RelativeLayout")),
                        1
                    )
                )
            )
            .atPosition(0)
        appCompatTextView.perform(click())

        val appCompatTextView2 = onData(anything())
            .inAdapterView(
                allOf(
                    withId(R.id.list_view_myLanguage),
                    childAtPosition(
                        withClassName(`is`("android.widget.RelativeLayout")),
                        1)
                )
            )
            .atPosition(1)
        appCompatTextView2.perform(click())

        val floatingActionButton = onView(
            allOf(
                withId(R.id.floating_add),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0),
                    3),
                isDisplayed()
                    )
        )
        floatingActionButton.perform(click())

        val linearLayout = onView(
            allOf(
                withId(R.id.linear),
                withParent(
                    allOf(
                        withId(android.R.id.custom),
                        withParent(IsInstanceOf.instanceOf(android.widget.FrameLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        linearLayout.check(matches(isDisplayed()))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
