package com.tes.eat.anywhere.shopping.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tes.eat.anywhere.shopping.data.local.ShoppingItem
import com.tes.eat.anywhere.shopping.data.remote.responses.ImageResponse
import com.tes.eat.anywhere.shopping.data.repository.ShoppingRepository
import com.tes.eat.anywhere.shopping.other.Constants
import com.tes.eat.anywhere.shopping.other.Event
import com.tes.eat.anywhere.shopping.other.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingViewModel @Inject constructor(
    //we use interface here instead of using default repository here and fakerespository in test
    private val repository:ShoppingRepository
):ViewModel() {

    val shoppingItem = repository.observeAllShoppingItems()

    val totalPrice=repository.observeTotalPrice()


    //we can only post value  to this live data in the viewModel
    private val _images =MutableLiveData<Event<Resource<ImageResponse>>>()
   //the actual live data we observe in our fragment which we can not post new value to live data
    val images: LiveData<Event<Resource<ImageResponse>>> =_images

    private val _curImageUrl =MutableLiveData<String>()

    val curImageUrl: LiveData<String> =_curImageUrl


    //to validate input text live
    private val _insertShoppingItemStatus =MutableLiveData<Event<Resource<ShoppingItem>>>()
    val insertShoppingItemStatus: LiveData<Event<Resource<ShoppingItem>>> =_insertShoppingItemStatus

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

    fun insertShoppingItem(name: String, amountString: String, priceString: String) {
        if(name.isEmpty() || amountString.isEmpty() || priceString.isEmpty()) {
            _insertShoppingItemStatus.postValue(Event(Resource.error("The fields must not be empty", null)))
            return
        }
        if(name.length > Constants.MAX_NAME_LENGTH) {
            _insertShoppingItemStatus.postValue(Event(Resource.error("The name of the item" +
                    "must not exceed ${Constants.MAX_NAME_LENGTH} characters", null)))
            return
        }
        if(priceString.length > Constants.MAX_PRICE_LENGTH) {
            _insertShoppingItemStatus.postValue(Event(Resource.error("The price of the item" +
                    "must not exceed ${Constants.MAX_PRICE_LENGTH} characters", null)))
            return
        }
        val amount = try {
            amountString.toInt()
        } catch(e: Exception) {
            _insertShoppingItemStatus.postValue(Event(Resource.error("Please enter a valid amount", null)))
            return
        }
        val shoppingItem = ShoppingItem(name, amount, priceString.toFloat(), _curImageUrl.value ?: "")

        insertShoppingItemIntoDb(shoppingItem)

        setCurImageUrl("")
        _insertShoppingItemStatus.postValue(Event(Resource.success(shoppingItem)))
    }

    fun searchForImage(imageQuery: String) {
        if(imageQuery.isEmpty()) {
            return
        }
        //.value instead of post value to make sure the observer get loading value or resource
        _images.value = Event(Resource.loading(null))
        viewModelScope.launch {
            val response = repository.searchForImage(imageQuery)
            _images.value = Event(response)
        }
    }
}