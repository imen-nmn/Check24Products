package com.inoomene.check24products.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.inoomene.check24products.R
import com.inoomene.check24products.data.Product
import com.inoomene.check24products.databinding.ItemProductBinding
import com.inoomene.check24products.databinding.ItemProductReleasedBinding
import com.inoomene.check24products.viewmodel.ProductItemViewModel
import java.util.*

class ProductRecycleViewAdapter : RecyclerView.Adapter<ProductRecycleViewAdapter.BaseViewHolder>() {
    private var products: List<Product> = Collections.emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        return when(viewType){
            TYPE_PRODUCT_DEFAULT_STATE ->  {
                val itemBinding: ItemProductBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_product,
                    parent,
                    false
                )
                return DefaultProductViewHolder(itemBinding)
            }

            TYPE_PRODUCT_RELEASED -> {
                val itemReleasedBinding: ItemProductReleasedBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_product_released,
                    parent,
                    false
                )
                return ReleasedProductViewHolder(itemReleasedBinding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = products[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun getItemViewType(position: Int): Int {
        val released = products[position].available
        if (released!!)
            return TYPE_PRODUCT_RELEASED
        return TYPE_PRODUCT_DEFAULT_STATE
    }

    fun update(list: List<Product>?) {
        products = list ?: Collections.emptyList()
        notifyDataSetChanged()
    }

    abstract class BaseViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        protected val productItemViewModel = ProductItemViewModel()
        abstract fun bind(item: Product)
    }



    class ReleasedProductViewHolder(private val binding: ItemProductReleasedBinding) : BaseViewHolder(binding.root) {
        override fun bind(item: Product) {
            productItemViewModel.bind(item)
            binding.productItemViewModel = productItemViewModel
        }
    }

    class DefaultProductViewHolder(private val binding: ItemProductBinding) : BaseViewHolder(binding.root) {
        override fun bind(item: Product) {
            productItemViewModel.bind(item)
            binding.productItemViewModel = productItemViewModel
        }
    }

    companion object {
        private const val TYPE_PRODUCT_DEFAULT_STATE = 0
        private const val TYPE_PRODUCT_RELEASED = 1
    }
}