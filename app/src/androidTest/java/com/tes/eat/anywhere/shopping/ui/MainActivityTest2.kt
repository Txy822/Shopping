package com.tes.eat.anywhere.shopping.ui


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.tes.eat.anywhere.shopping.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest2 {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun mainActivityTest2() {
        val floatingActionButton = onView(
            allOf(
                withId(R.id.fabAddShoppingItem),
                childAtPosition(
                    allOf(
                        withId(R.id.rootLayout),
                        childAtPosition(
                            withId(R.id.navHostFragment),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        floatingActionButton.perform(click())

        val textInputEditText = onView(
            allOf(
                withId(R.id.etShoppingItemName),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.textInputLayout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText.perform(replaceText("Orange"), closeSoftKeyboard())

        val textInputEditText2 = onView(
            allOf(
                withId(R.id.etShoppingItemAmount),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.textInputLayout3),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText2.perform(replaceText("10"), closeSoftKeyboard())

        val textInputEditText3 = onView(
            allOf(
                withId(R.id.etShoppingItemPrice),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.textInputLayout2),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText3.perform(replaceText("0.45"), closeSoftKeyboard())

        val materialButton = onView(
            allOf(
                withId(R.id.btnAddShoppingItem), withText("Add"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.navHostFragment),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())

        val floatingActionButton2 = onView(
            allOf(
                withId(R.id.fabAddShoppingItem),
                childAtPosition(
                    allOf(
                        withId(R.id.rootLayout),
                        childAtPosition(
                            withId(R.id.navHostFragment),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        floatingActionButton2.perform(click())

        val textInputEditText4 = onView(
            allOf(
                withId(R.id.etShoppingItemName),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.textInputLayout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText4.perform(replaceText("Banana"), closeSoftKeyboard())

        val textInputEditText5 = onView(
            allOf(
                withId(R.id.etShoppingItemAmount),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.textInputLayout3),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText5.perform(replaceText("20"), closeSoftKeyboard())

        val textInputEditText6 = onView(
            allOf(
                withId(R.id.etShoppingItemPrice),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.textInputLayout2),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText6.perform(replaceText("3"), closeSoftKeyboard())

        val materialButton2 = onView(
            allOf(
                withId(R.id.btnAddShoppingItem), withText("Add"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.navHostFragment),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        materialButton2.perform(click())
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
