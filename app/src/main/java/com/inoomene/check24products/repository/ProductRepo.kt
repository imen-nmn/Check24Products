package com.inoomene.check24products.repository

import com.inoomene.check24products.data.Product
import com.inoomene.check24products.data.ProductResult
import io.reactivex.Flowable

interface ProductRepo {

    fun getProductResult(): Flowable<ProductResult>

    fun getAvailableProducts(): Flowable<List<Product>>

    fun getFavoriteProducts(): Flowable<List<Product>>

}