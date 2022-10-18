package com.tes.eat.anywhere.shopping.ui

import com.tes.eat.anywhere.shopping.repository.FakeShoppingRepository
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class ShoppingViewModelTest {
    private lateinit var viewModel: ShoppingViewModel

    @Before
    fun setUp() {
        viewModel= ShoppingViewModel(FakeShoppingRepository())
    }

    @Test
    fun getShpooingItems() {
    }

    @Test
    fun getTotalPrice() {
    }

    @Test
    fun getImages() {
    }

    @Test
    fun getCurImageUrl() {
    }

    @Test
    fun getInsertShoppingItemStatus() {
    }

    @Test
    fun setCurImageUrl() {
    }

    @Test
    fun deleteShoppingItem() {
    }

    @Test
    fun insertShoppingItemIntoDb() {
    }

    @Test
    fun insertShoppingItem() {
    }

    @Test
    fun searchForImage() {
    }


    @After
    fun tearDown() {
    }

}