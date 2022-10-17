package com.tes.eat.anywhere.shopping.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ShoppingDao {

    @Insert
    suspend fun insertShoppingItems(shoppingItem: ShoppingItem)


    @Delete
    suspend fun deleteShoppingItems(shoppingItem: ShoppingItem)

    @Query("SELECT * FROM shopping_item")
    fun observeAllShoppingItem():LiveData<List<ShoppingItem>>

    @Query("SELECT SUM(price*amount)FROM shopping_item")
    fun observablePrice():LiveData<Float>
}