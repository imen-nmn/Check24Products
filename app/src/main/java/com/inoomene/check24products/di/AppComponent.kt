package com.inoomene.check24products.di

import com.inoomene.check24products.viewmodel.ProductListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ProductApiModule::class, ProductRepoModule::class])
interface AppComponent {

    fun inject(productListViewModel: ProductListViewModel)

    @Component.Builder
    interface Builder {
        fun build() : AppComponent
        fun productRepoModule(productRepoModule: ProductRepoModule) : Builder
        fun productApiModule(productApiModule: ProductApiModule) : Builder
    }
}