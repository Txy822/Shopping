package com.tes.eat.anywhere.shopping.di

import android.content.Context
import androidx.room.Room
import com.tes.eat.anywhere.shopping.data.local.ShoppingDao
import com.tes.eat.anywhere.shopping.data.local.ShoppingItemDatabase
import com.tes.eat.anywhere.shopping.data.remote.PixabayAPI
import com.tes.eat.anywhere.shopping.data.repository.DefaultShoppingRepository
import com.tes.eat.anywhere.shopping.data.repository.ShoppingRepository
import com.tes.eat.anywhere.shopping.other.Constants.BASE_URL
import com.tes.eat.anywhere.shopping.other.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) //was ApplicationComponents
object AppModule {

    @Singleton
    @Provides
    fun providesShoppingItemDatabase(
        @ApplicationContext context: Context
    )= Room.databaseBuilder(context,ShoppingItemDatabase::class.java,DATABASE_NAME).build()

    //as we provide inteface of repository dagger doesn't know that how to create default repository
    //for such shopping repository
    @Singleton
    @Provides
    fun provideDefaultShoppingRepository(
dao:ShoppingDao,
api:PixabayAPI
    )=DefaultShoppingRepository(dao,api)as ShoppingRepository

    @Singleton
    @Provides
    fun provideShoppingDao(
        database: ShoppingItemDatabase
    )=database.shoppingDao()


    @Singleton
    @Provides
    fun providesPixabayApi():PixabayAPI{
        return  Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PixabayAPI::class.java)
    }
}