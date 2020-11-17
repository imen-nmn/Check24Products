package com.inoomene.check24products.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.inoomene.check24products.R
import com.inoomene.check24products.data.Product
import com.inoomene.check24products.databinding.FragmentProductListBinding
import com.inoomene.check24products.viewmodel.ProductListViewModel
import kotlinx.android.synthetic.main.fragment_product_list.view.*

class ProductListFragment : Fragment() {

    private var listener: OnProductListFragmentListener? = null
    lateinit var productListViewModel: ProductListViewModel
    lateinit var productRecycleViewAdapter: ProductRecycleViewAdapter
    lateinit var binding  : FragmentProductListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productListViewModel = ViewModelProvider(this).get(ProductListViewModel::class.java)
        listener?.inject(productListViewModel)
        productListViewModel.requestProductResult()
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_list, container, false)
        binding.productListViewModel = productListViewModel
        binding.lifecycleOwner = this
        val view = binding.root
        productRecycleViewAdapter = ProductRecycleViewAdapter()
        view.productRecycleView?.adapter = productRecycleViewAdapter
        productListViewModel.getProductListLiveData().observe(viewLifecycleOwner, {
            productRecycleViewAdapter.update(it)
        })
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnProductListFragmentListener) {
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnProductListFragmentListener {
        fun redirectToProductDetail(product: Product)
        fun setTitle(s: CharSequence)
        fun inject(viewModel: ProductListViewModel)
    }
}