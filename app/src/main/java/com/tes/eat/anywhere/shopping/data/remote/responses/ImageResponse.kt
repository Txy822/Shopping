package com.tes.eat.anywhere.shopping.data.remote.responses



data class ImageResponse(
    val imageResponse: List<ImageResult> = listOf(),
    val total: Int = 0,
    val totalHits: Int = 0
)