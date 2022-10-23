package com.tes.eat.anywhere.shopping.ui
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tes.eat.anywhere.shopping.R

import androidx.fragment.app.FragmentFactory
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.tes.eat.anywhere.shopping.adapters.ImageAdapter
import com.tes.eat.anywhere.shopping.launchFragmentInHiltContainer
import com.tes.eat.anywhere.shopping.repository.FakeShoppingRepositoryAndroidTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi

import com.google.common.truth.Truth.assertThat
import com.tes.eat.anywhere.shopping.data.getOrAwaitValue

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import javax.inject.Inject

@HiltAndroidTest
@MediumTest
@ExperimentalCoroutinesApi
class ImagePickFragmentTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule =HiltAndroidRule(this)

    //factory need to create correct fragment
    lateinit var  fragmentFactory:FragmentFactory

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun clickImage_popBackStackSetImageUrl(){
        val navController=mock(NavController::class.java)
        val imageUrl="TEST"

        val testViewModel= ShoppingViewModel(FakeShoppingRepositoryAndroidTest())

        launchFragmentInHiltContainer<ImagePickFragment>(fragmentFactory=fragmentFactory){
            Navigation.setViewNavController(requireView(),navController)
            imageAdapter.images= listOf(imageUrl)
            viewModel=testViewModel
        }

        onView(withId(R.id.rvImages)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ImageAdapter.ImageViewHolder>(
                0,
                click()
            )
        )
        verify(navController).popBackStack()
        assertThat(testViewModel.curImageUrl.getOrAwaitValue()).isEqualTo(imageUrl)
    }

    @After
    fun tearDown() {
    }
}