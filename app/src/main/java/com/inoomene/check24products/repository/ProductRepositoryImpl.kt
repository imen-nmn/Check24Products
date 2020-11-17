package com.inoomene.check24products.repository

import com.inoomene.check24products.data.Product
import com.inoomene.check24products.data.ProductResult
import com.inoomene.check24products.remote.ProductApi
import io.reactivex.Flowable
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val productApi: ProductApi): ProductRepo {

    override fun getProductResult(): Flowable<ProductResult> {
        return productApi.getProductResult()
    }

    override fun getAvailableProducts(): Flowable<List<Product>> {
        TODO("Not yet implemented")
    }

    override fun getFavoriteProducts(): Flowable<List<Product>> {
        TODO("Not yet implemented")
    }
}