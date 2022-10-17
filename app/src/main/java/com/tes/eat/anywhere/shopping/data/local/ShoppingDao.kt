package com.tes.eat.anywhere.shopping.data.local

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

interface ShoppingDao {

    @Insert
    suspend fun insertShoppingItems(shoppingItem: ShoppingItem)


    @Delete
    suspend fun dShoppingItems(shoppingItem: ShoppingItem)

    @Query("SELECT * FROM shopping_items")
    fun observeAllShoppingItem():LiveData<List<ShoppingItem>>

    @Query("SELECT SUM(price*amount)FROM shopping_items")
    fun observablePrice():LiveData<Float>
}