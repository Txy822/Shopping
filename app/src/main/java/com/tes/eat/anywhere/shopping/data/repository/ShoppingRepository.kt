package com.tes.eat.anywhere.shopping.data.repository

import androidx.lifecycle.LiveData
import com.tes.eat.anywhere.shopping.data.local.ShoppingItem
import com.tes.eat.anywhere.shopping.data.remote.responses.ImageResponse
import com.tes.eat.anywhere.shopping.other.Resource
import retrofit2.Response

interface ShoppingRepository {
    suspend fun insertShoppingItem(shoppingItem:ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeAllShoppingItems():LiveData<List<ShoppingItem>>

    fun observeTotalPrice():LiveData<Float>

    suspend fun searchForImage(imageQuery:String): Resource<ImageResponse>


}