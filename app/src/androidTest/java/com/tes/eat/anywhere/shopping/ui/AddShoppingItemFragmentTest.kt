package com.tes.eat.anywhere.shopping.ui

import androidx.navigation.NavController
import androidx.test.espresso.Espresso.pressBack
import androidx.test.filters.MediumTest
import com.tes.eat.anywhere.shopping.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi


import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify



@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class AddShoppingItemFragmentTest {
    @get:Rule
    var hiltRule= HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltRule.inject()
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