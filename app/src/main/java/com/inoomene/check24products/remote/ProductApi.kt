package com.inoomene.check24products.remote

import com.inoomene.check24products.data.ProductResult
import io.reactivex.Flowable
import retrofit2.http.GET

interface ProductApi {

    @GET("products-test.json")
    fun getProductResult(): Flowable<ProductResult>
}