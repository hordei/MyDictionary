package com.eb.mydictionaryApp.ui


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.eb.mydictionaryApp.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.*
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class CheckIfTestingWorking {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SplashScreen::class.java)

    @Test
    fun checkIfTestingWorking() {
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
            .atPosition(6)
        appCompatTextView.perform(click())

        val appCompatTextView2 = onData(anything())
            .inAdapterView(
                allOf(
                    withId(R.id.list_view_myLanguage),
                    childAtPosition(
                        withClassName(`is`("android.widget.RelativeLayout")),
                        1
                    )
                )
            )
            .atPosition(10)
        appCompatTextView2.perform(click())

        val floatingActionButton = onView(
            allOf(
                withId(R.id.floating_add),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        floatingActionButton.perform(click())

        val appCompatEditText = onView(
            allOf(
                withId(R.id.edt_word),
                childAtPosition(
                    allOf(
                        withId(R.id.linear),
                        childAtPosition(
                            withId(android.R.id.custom),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("a"), closeSoftKeyboard())

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.edt_meaning),
                childAtPosition(
                    allOf(
                        withId(R.id.linear),
                        childAtPosition(
                            withId(android.R.id.custom),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatEditText2.perform(replaceText("a"), closeSoftKeyboard())

        val appCompatButton = onView(
            allOf(
                withId(R.id.btn_addWord), withText("Add"),
                childAtPosition(
                    allOf(
                        withId(R.id.linear),
                        childAtPosition(
                            withId(android.R.id.custom),
                            0
                        )
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatButton.perform(click())

        val floatingActionButton2 = onView(
            allOf(
                withId(R.id.floating_add),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        floatingActionButton2.perform(click())

        val appCompatEditText3 = onView(
            allOf(
                withId(R.id.edt_word),
                childAtPosition(
                    allOf(
                        withId(R.id.linear),
                        childAtPosition(
                            withId(android.R.id.custom),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText3.perform(replaceText("d"), closeSoftKeyboard())

        val appCompatEditText4 = onView(
            allOf(
                withId(R.id.edt_meaning),
                childAtPosition(
                    allOf(
                        withId(R.id.linear),
                        childAtPosition(
                            withId(android.R.id.custom),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatEditText4.perform(replaceText("d"), closeSoftKeyboard())

        val appCompatButton2 = onView(
            allOf(
                withId(R.id.btn_addWord), withText("Add"),
                childAtPosition(
                    allOf(
                        withId(R.id.linear),
                        childAtPosition(
                            withId(android.R.id.custom),
                            0
                        )
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatButton2.perform(click())

        val floatingActionButton3 = onView(
            allOf(
                withId(R.id.floating_add),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        floatingActionButton3.perform(click())

        val appCompatEditText5 = onView(
            allOf(
                withId(R.id.edt_word),
                childAtPosition(
                    allOf(
                        withId(R.id.linear),
                        childAtPosition(
                            withId(android.R.id.custom),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText5.perform(replaceText("e"), closeSoftKeyboard())

        val appCompatEditText6 = onView(
            allOf(
                withId(R.id.edt_meaning),
                childAtPosition(
                    allOf(
                        withId(R.id.linear),
                        childAtPosition(
                            withId(android.R.id.custom),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatEditText6.perform(replaceText("e"), closeSoftKeyboard())

        val appCompatButton3 = onView(
            allOf(
                withId(R.id.btn_addWord), withText("Add"),
                childAtPosition(
                    allOf(
                        withId(R.id.linear),
                        childAtPosition(
                            withId(android.R.id.custom),
                            0
                        )
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatButton3.perform(click())

        val floatingActionButton4 = onView(
            allOf(
                withId(R.id.floating_add),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        floatingActionButton4.perform(click())

        val appCompatEditText7 = onView(
            allOf(
                withId(R.id.edt_word),
                childAtPosition(
                    allOf(
                        withId(R.id.linear),
                        childAtPosition(
                            withId(android.R.id.custom),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText7.perform(replaceText("f"), closeSoftKeyboard())

        val appCompatEditText8 = onView(
            allOf(
                withId(R.id.edt_meaning),
                childAtPosition(
                    allOf(
                        withId(R.id.linear),
                        childAtPosition(
                            withId(android.R.id.custom),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatEditText8.perform(replaceText("f"), closeSoftKeyboard())

        val appCompatButton4 = onView(
            allOf(
                withId(R.id.btn_addWord), withText("Add"),
                childAtPosition(
                    allOf(
                        withId(R.id.linear),
                        childAtPosition(
                            withId(android.R.id.custom),
                            0
                        )
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatButton4.perform(click())

        val floatingActionButton5 = onView(
            allOf(
                withId(R.id.floating_add),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        floatingActionButton5.perform(click())

        val appCompatEditText9 = onView(
            allOf(
                withId(R.id.edt_word),
                childAtPosition(
                    allOf(
                        withId(R.id.linear),
                        childAtPosition(
                            withId(android.R.id.custom),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText9.perform(replaceText("d"), closeSoftKeyboard())

        val appCompatEditText10 = onView(
            allOf(
                withId(R.id.edt_word), withText("d"),
                childAtPosition(
                    allOf(
                        withId(R.id.linear),
                        childAtPosition(
                            withId(android.R.id.custom),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText10.perform(click())

        val appCompatEditText11 = onView(
            allOf(
                withId(R.id.edt_meaning),
                childAtPosition(
                    allOf(
                        withId(R.id.linear),
                        childAtPosition(
                            withId(android.R.id.custom),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatEditText11.perform(replaceText("d"), closeSoftKeyboard())

        val appCompatButton5 = onView(
            allOf(
                withId(R.id.btn_addWord), withText("Add"),
                childAtPosition(
                    allOf(
                        withId(R.id.linear),
                        childAtPosition(
                            withId(android.R.id.custom),
                            0
                        )
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatButton5.perform(click())

        val appCompatImageButton = onView(
            allOf(
                withContentDescription("Hamburger button"),
                childAtPosition(
                    allOf(
                        withId(R.id.topbar),
                        childAtPosition(
                            withClassName(`is`("android.widget.RelativeLayout")),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatImageButton.perform(click())

        val recyclerView = onView(
            withClassName(`is`("androidx.recyclerview.widget.RecyclerView"))
        )
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(1, click()))

        val appCompatButton6 = onView(
            allOf(
                withId(R.id.btnOpt3), withText("a"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.RelativeLayout")),
                        1
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatButton6.perform(click())

        val appCompatButton7 = onView(
            allOf(
                withId(R.id.btnOpt1), withText("d"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.RelativeLayout")),
                        1
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatButton7.perform(click())

        val appCompatButton8 = onView(
            allOf(
                withId(R.id.btnOpt1), withText("e"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.RelativeLayout")),
                        1
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatButton8.perform(click())

        val appCompatButton9 = onView(
            allOf(
                withId(R.id.btnOpt2), withText("f"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.RelativeLayout")),
                        1
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatButton9.perform(click())

        val appCompatButton10 = onView(
            allOf(
                withId(R.id.btnOpt2), withText("d"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.RelativeLayout")),
                        1
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatButton10.perform(click())

        val appCompatButton11 = onView(
            allOf(
                withId(android.R.id.button1), withText("Ok"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    3
                )
            )
        )
        appCompatButton11.perform(scrollTo(), click())
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
