package com.tes.eat.anywhere.shopping.data.remote

import retrofit2.http.Query
import com.tes.eat.anywhere.shopping.BuildConfig
import com.tes.eat.anywhere.shopping.data.remote.responses.ImageResponse
import retrofit2.Response
import retrofit2.http.GET

interface PixabayAPI {

    @GET("/api/")
    suspend fun searchForImage(
        @Query("q") serachQuery:String,
        @Query("key") apiKey:String =BuildConfig.API_KEY
    ):Response<ImageResponse>
}