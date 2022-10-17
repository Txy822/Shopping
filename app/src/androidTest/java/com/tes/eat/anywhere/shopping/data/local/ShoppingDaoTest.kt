package com.tes.eat.anywhere.shopping.data.local


import com.google.common.truth.Truth.assertThat
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.tes.eat.anywhere.shopping.data.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import androidx.arch.core.executor.testing.InstantTaskExecutorRule

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


//why this annotation?
@ExperimentalCoroutinesApi
//to tell run on emulator
@RunWith(AndroidJUnit4::class)
//to tell that we are writing unit test
@SmallTest //it would have been mediumTest if to test instrument

class ShoppingDaoTest {

    @get:Rule
    var instantTaskExecutorRule =InstantTaskExecutorRule()

    private lateinit var database:ShoppingItemDatabase
    private lateinit var dao:ShoppingDao

    @Before
    fun setUp(){
        //to tell not real storage and it stores in RAM but not in persistance storage
        database= Room.inMemoryDatabaseBuilder(
        ApplicationProvider.getApplicationContext(), //to get reference to the context inside of test case
        ShoppingItemDatabase::class.java
        ).allowMainThreadQueries().build() //run in the main thread
        dao=database.shoppingDao()
    }

    /*
    run blocking for coroutine, to run coroutine inside main thread.
     Coroutine blocks the main thread by suspend calls
     */
    @Test
    fun insertShoppingItem()= runBlocking{
        val shoppingItem=ShoppingItem("name",1,1f,"url",1)
        dao.insertShoppingItem(shoppingItem)

        //list of shopping items this observe function returns
        val allShoppingItems=dao.observeAllShoppingItems().getOrAwaitValue()
        assertThat(allShoppingItems).contains(shoppingItem)
    }

    @Test
    fun deleteShoppingItem()= runBlocking {
        //we can create the same id as after each test everything is deleted
        val shoppingItem=ShoppingItem("name",1,1f,"url",1)
        dao.insertShoppingItem(shoppingItem)
        dao.deleteShoppingItem(shoppingItem)
        val allShoppingItems=dao.observeAllShoppingItems().getOrAwaitValue()

        assertThat(allShoppingItems).doesNotContain(shoppingItem)
    }

    @Test
    fun observeTotalPrice()= runBlocking {
        val shoppingItem1=ShoppingItem("name",3,1f,"url",1)
        val shoppingItem2=ShoppingItem("name",4,1.5f,"url",2)
        val shoppingItem3=ShoppingItem("name",5,1f,"url",3)

        dao.insertShoppingItem(shoppingItem1)
        dao.insertShoppingItem(shoppingItem2)
        dao.insertShoppingItem(shoppingItem3)

        val tottalPriceSum=dao.observeTotalPrice().getOrAwaitValue()
        assertThat(tottalPriceSum).isEqualTo(3*1f+4*1.5f+5*1f)
    }

    @After
    fun tearDown(){
        database.close()
    }

}

