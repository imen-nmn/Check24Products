package com.inoomene.check24products.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.inoomene.check24products.R
import com.inoomene.check24products.data.Product
import com.inoomene.check24products.di.AppComponent
import com.inoomene.check24products.di.DaggerAppComponent
import com.inoomene.check24products.di.ProductApiModule
import com.inoomene.check24products.di.ProductRepoModule
import com.inoomene.check24products.viewmodel.ProductListViewModel

class MainActivity : AppCompatActivity(), ProductListFragment.OnProductListFragmentListener {

    private lateinit var appComponent: AppComponent
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        navController = Navigation.findNavController(
            this,
            R.id.nav_host_fragment
        )

        appComponent = DaggerAppComponent.builder()
            .productApiModule(ProductApiModule)
            .productRepoModule(ProductRepoModule())
            .build()

    }

    override fun redirectToProductDetail(product: Product) {
        navController.navigate(
            R.id.action_ProductList_to_ProductDetail,
            bundleOf(
                ProductDetailFragment.ARG_PRODUCT to product,
            )
        )
    }

    override fun inject(viewModel: ProductListViewModel) {
        appComponent.inject(viewModel)
    }
}