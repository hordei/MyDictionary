package com.eb.mydictionaryApp.ui


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
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
class CanWeChangeInputedWords {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SplashScreen::class.java)

    @Test
    fun canWeChangeInputedWords() {
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
                        1
                    )
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

        val linearLayout = onView(
            allOf(
                withId(R.id.linear_row),
                childAtPosition(
                    withParent(withId(R.id.words_list_view)),
                    0
                ),
                isDisplayed()
            )
        )
        linearLayout.perform(click())

        val linearLayout2 = onView(
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
        linearLayout2.check(matches(isDisplayed()))

        val appCompatEditText3 = onView(
            allOf(
                withId(R.id.edt_meaning), withText("a"),
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
        appCompatEditText3.perform(replaceText("x"))

        val appCompatEditText4 = onView(
            allOf(
                withId(R.id.edt_meaning), withText("x"),
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
        appCompatEditText4.perform(closeSoftKeyboard())

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

        val textView = onView(
            allOf(
                withId(R.id.txt_meaning), withText("x"),
                withParent(
                    allOf(
                        withId(R.id.linear_row),
                        withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        textView.check(matches(isDisplayed()))
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
