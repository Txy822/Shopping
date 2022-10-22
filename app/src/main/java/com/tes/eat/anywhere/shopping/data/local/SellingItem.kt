package com.tes.eat.anywhere.shopping.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Selling_item")
data class SellingItem(

    @ColumnInfo(name = "name")
    var name:String,

    @ColumnInfo(name = "amount")
    var amount:Int,

    @ColumnInfo(name = "price")
    var price:Float,

    @ColumnInfo(name = "image_url")
    var imageUrl:String,

    @PrimaryKey(autoGenerate=true)
    val id:Int?=null,

    ){
}
