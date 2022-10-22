package com.tes.eat.anywhere.shopping.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.tes.eat.anywhere.shopping.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import com.google.common.truth.Truth.assertThat
import com.tes.eat.anywhere.shopping.R
import com.tes.eat.anywhere.shopping.adapters.ShoppingAdapter
import com.tes.eat.anywhere.shopping.data.getOrAwaitValue
import com.tes.eat.anywhere.shopping.data.local.ShoppingItem
import org.checkerframework.checker.units.qual.s

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import javax.inject.Inject

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi

/*
Here we are going to use mockito for testing which is another type of test double like fake
 Mock is also popular. Mock object is a class that has only function signature.
 we use mock libraries to create mock object from nav controller and  fragment to keep track of which function we call. Then simu

 */
class ShoppingFragmentTest {
@get:Rule
var hiltRule=HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var  testFragmentFactory: TestShoppingFragmentFactory
    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun swipeShoppingItem_deleteInDb(){
        val shoppingItem =ShoppingItem("test",1,1f,"test,1")
        var testViewModel:ShoppingViewModel? =null
        launchFragmentInHiltContainer<ShoppingFragment>(
            fragmentFactory = testFragmentFactory
        ) {
            testViewModel=viewModel
            viewModel?.insertShoppingItemIntoDb(shoppingItem)
        }

        onView(withId(R.id.rvShoppingItems)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ShoppingAdapter.ImageViewHolder>(
                0,
                swipeLeft()
            )
        )
        assertThat(testViewModel?.shoppingItem?.getOrAwaitValue()).isEmpty()

    }

    @Test
    fun clickAddShoppingItemButton_navigateToAddShoppingItemFragment(){
        val navController=mock(NavController::class.java)
        launchFragmentInHiltContainer<ShoppingFragment> {
            Navigation.setViewNavController(requireView(),navController)
        }
        onView(withId(R.id.fabAddShoppingItem)).perform(click())
        verify(navController).navigate(
            ShoppingFragmentDirections.actionShoppingFragmentToAddShoppingItemFragment()
        )
    }

    @After
    fun tearDown() {
    }
}