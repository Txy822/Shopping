package com.tes.eat.anywhere.shopping.ui
import com.tes.eat.anywhere.shopping.R

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.tes.eat.anywhere.shopping.launchFragmentInHiltContainer
import com.tes.eat.anywhere.shopping.repository.FakeShoppingRepositoryAndroidTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi


import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import javax.inject.Inject


@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class AddShoppingItemFragmentTest {
    @get:Rule
    var hiltRule= HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule =InstantTaskExecutorRule()

    @Inject
    lateinit var fragmentFactory : ShoppingFragmentFactory

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun clickInsertIntoDb_shoppingItemInsertedIntoDb(){
        val testViewModel=ShoppingViewModel(FakeShoppingRepositoryAndroidTest())
        launchFragmentInHiltContainer<AddShoppingItemFragment>(
            fragmentFactory = fragmentFactory
        ) {
            viewModel =testViewModel
        }
        onView(withId(R.id.etShoppingItemName)).perform(replaceText("Shopping Item"))
        onView(withId(R.id.etShoppingItemAmount)).perform(replaceText("5"))
        onView(withId(R.id.etShoppingItemPrice)).perform(replaceText("5.4"))
        onView(withId(R.id.btnAddShoppingItem)).perform(click())

    }

    @Test
    fun pressBackButton_popBackStack(){
        val navController=mock(NavController::class.java)
        launchFragmentInHiltContainer<AddShoppingItemFragment> {
        }

        pressBack()

        verify(navController).popBackStack()

    }


    @After
    fun tearDown() {
    }
}