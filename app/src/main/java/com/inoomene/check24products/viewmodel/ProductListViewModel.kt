package com.inoomene.check24products.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.inoomene.check24products.data.Product
import com.inoomene.check24products.repository.ProductRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProductListViewModel : ViewModel(){

    @Inject
    lateinit var productRepo : ProductRepo

    private val productListLiveData = MutableLiveData<List<Product>>()
     val headerTitleLiveData = MutableLiveData<String>()
     val headerDescriptionLiveData = MutableLiveData<String>()

    private val compositeDisposable = CompositeDisposable()


    fun requestProductResult(){
        val productDisposable =  productRepo.getProductResult()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {   Log.i("ProductListViewModel", "doOnSubscribe")}
            .subscribe({ response ->
                Log.i("ProductListViewModel", "onNext : $response")
                productListLiveData.value = response.productList
                headerTitleLiveData.value = response.header?.headerTitle
                headerDescriptionLiveData.value = response.header?.headerDescription
            }, { error ->
                Log.e("ProductListViewModel", "onError  : $error")
            })

        compositeDisposable.add(productDisposable)
    }


    fun getProductListLiveData() = productListLiveData

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}