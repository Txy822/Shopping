package com.tes.eat.anywhere.shopping.data.repository

import androidx.lifecycle.LiveData
import com.tes.eat.anywhere.shopping.data.local.ShoppingDao
import com.tes.eat.anywhere.shopping.data.local.ShoppingItem
import com.tes.eat.anywhere.shopping.data.remote.PixabayAPI
import com.tes.eat.anywhere.shopping.data.remote.responses.ImageResponse
import com.tes.eat.anywhere.shopping.other.Resource
import retrofit2.Response
import javax.inject.Inject

class DefaultShoppingRepository @Inject constructor(
    private val shoppingDao:ShoppingDao,
    private val pixabayAPI:PixabayAPI
):ShoppingRepository {
    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.insertShoppingItem((shoppingItem))
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.deleteShoppingItem(shoppingItem)
    }

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItem>> {
        return shoppingDao.observeAllShoppingItems()
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return shoppingDao.observeTotalPrice()
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return try {
            val response = pixabayAPI.searchForImage(imageQuery)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("An unknown error occured", null)
            } else {
                Resource.error("An unknown error", null)
            }

        } catch (e: Exception) {
            Resource.error("couldn't reach server",null)
        }

    }

}