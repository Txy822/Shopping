package com.tes.eat.anywhere.shopping.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tes.eat.anywhere.shopping.data.local.ShoppingItem
import com.tes.eat.anywhere.shopping.data.remote.responses.ImageResponse
import com.tes.eat.anywhere.shopping.data.repository.ShoppingRepository
import com.tes.eat.anywhere.shopping.other.Event
import com.tes.eat.anywhere.shopping.other.Resource
import kotlinx.coroutines.launch

class ShoppingViewModel @ViewModelInject constructor(
    //we use interface here instead of using default repository here and fakerespository in test
    private val repository:ShoppingRepository
):ViewModel() {

    val shpooingItems=repository.observeAllShoppingItems()

    val totalPrice=repository.observeTotalPrice()


    //we can only post value  to this live data in the viewModel
    private val _images =MutableLiveData<Event<Resource<ImageResponse>>>()
   //the actual live data we observe in our fragment which we can not post new value to live data
    val images: LiveData<Event<Resource<ImageResponse>>> =_images

    private val _curImageUrl =MutableLiveData<String>()

    val curImageUrl: LiveData<String> =_curImageUrl


    //to validate input text live
    private val _insertShoppingItemStatus =MutableLiveData<Event<Resource<ImageResponse>>>()
    val insertShoppingItemStatus: LiveData<Event<Resource<ImageResponse>>> =_insertShoppingItemStatus

    fun setCurImageUrl(url:String){
        _curImageUrl.postValue(url)
    }

    //to delete item
    fun deleteShoppingItem(shoppingItem:ShoppingItem) = viewModelScope.launch{
        repository.insertShoppingItem(shoppingItem)

    }

    //validating items
    fun insertShoppingItemIntoDb(shoppingItem:ShoppingItem) = viewModelScope.launch {
        repository.insertShoppingItem(shoppingItem)
    }

    fun insertShoppingItem(name:String,amountString:String,priceString:String){

    }

    fun searchForImage(imageQuery:String){

    }
}