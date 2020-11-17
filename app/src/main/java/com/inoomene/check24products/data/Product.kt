package com.inoomene.check24products.data

import com.squareup.moshi.Json

data class Product(
    val name: String?,

    val type: String?,

    val id: Int?,

    val color: String?,

    val imageURL: String?,

    val colorCode: String?,

    val available: Boolean?,

    val releaseDate: Long?,

    val description: String?,

    val longDescription: String?,

    val rating: Float?,

    @field:Json(name = "price")
    val price: Price?
)