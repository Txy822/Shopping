package com.tes.eat.anywhere.shopping.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

//Teach about database and testing of all
@Database(
    entities=[ShoppingItem::class],
    version=1 )
abstract class ShoppingItemDatabase: RoomDatabase() {
    abstract fun shoppingDao():ShoppingDao
}
