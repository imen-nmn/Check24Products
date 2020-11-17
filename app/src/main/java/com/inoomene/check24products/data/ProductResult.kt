package com.inoomene.check24products.data

import com.squareup.moshi.Json

data class ProductResult(
    val header: Header?,

    @field:Json(name = "products")
    val productList: List<Product>?
)