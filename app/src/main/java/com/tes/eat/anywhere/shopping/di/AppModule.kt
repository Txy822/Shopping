package com.tes.eat.anywhere.shopping.di

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tes.eat.anywhere.shopping.R
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


    @Provides
    @Singleton
    fun providesShoppingItemDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, ShoppingItemDatabase::class.java, DATABASE_NAME).build()

    //as we provide inteface of repository dagger doesn't know that how to create default repository
    //for such shopping repository

    @Provides
    @Singleton
    fun provideDefaultShoppingRepository(
        dao: ShoppingDao,
        api: PixabayAPI
    ) = DefaultShoppingRepository(dao, api) as ShoppingRepository

    @Singleton
    @Provides
    fun provideGlideInstance(
        @ApplicationContext context: Context
    )= Glide.with(context).setDefaultRequestOptions(
        RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
    )


    @Provides
    @Singleton
    fun provideShoppingDao(
        database: ShoppingItemDatabase
    ) = database.shoppingDao()


    @Provides
    @Singleton
    fun providesPixabayApi(): PixabayAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PixabayAPI::class.java)
    }
}