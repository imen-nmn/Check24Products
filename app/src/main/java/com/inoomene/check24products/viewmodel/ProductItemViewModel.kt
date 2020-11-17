package com.inoomene.check24products.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.inoomene.check24products.data.Price
import com.inoomene.check24products.data.Product
import java.text.SimpleDateFormat
import java.util.*

class ProductItemViewModel : ViewModel() {

    val productName = MutableLiveData<String>()
    val productImageUrl = MutableLiveData<String>()
    val productReleaseDate = MutableLiveData<String>()
    val productDescription = MutableLiveData<String>()
    val productPrice = MutableLiveData<String>()
    val productRating = MutableLiveData<Float>()


    fun bind(product: Product) {
        productName.value = product.name
        productImageUrl.value = product.imageURL
        productReleaseDate.value = timeStampToDate(product.releaseDate)
        productDescription.value = product.description
        productRating.value = product.rating
        productPrice.value = priceDescription(product.price)
    }


    @SuppressLint("SimpleDateFormat")
    private fun timeStampToDate(timestamp: Long?): String {
        if (timestamp == null) return ""
        val dateFormat = SimpleDateFormat("dd.MM.yyyy")
        val date = Date(timestamp * 1000)
        return dateFormat.format(date)
    }

    private fun priceDescription(price: Price?): String {
        if (price == null) return ""
        return "Preis: ${price.value} ${price.currency}"
    }

}