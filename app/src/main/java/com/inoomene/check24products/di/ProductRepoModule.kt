package com.inoomene.check24products.di

import com.inoomene.check24products.remote.ProductApi
import com.inoomene.check24products.repository.ProductRepo
import com.inoomene.check24products.repository.ProductRepositoryImpl
import dagger.Module
import dagger.Provides


@Module
class ProductRepoModule {

    @Provides
    fun provideProductRepo(productApi: ProductApi): ProductRepo = ProductRepositoryImpl(productApi)
}