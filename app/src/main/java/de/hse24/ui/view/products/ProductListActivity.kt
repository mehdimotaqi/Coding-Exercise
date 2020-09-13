package de.hse24.ui.view.products

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import de.hse24.R
import de.hse24.data.model.Product
import de.hse24.ui.adapters.ProductsListAdapter
import de.hse24.ui.showToast
import kotlinx.android.synthetic.main.activity_product_list.*

class ProductListActivity : AppCompatActivity(), ProductListView {

    private var categoryId : String? = null
    private var productListPresenter: ProductListPresenter? = null
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var productsListAdapter: ProductsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)
        gridLayoutManager = GridLayoutManager(this, 2)
        product_recyclerView.layoutManager = gridLayoutManager
        categoryId = intent.getStringExtra("CATEGORYID")
        productListPresenter = ProductListPresenter(this)
        productListPresenter?.getProductList(categoryId!!)

    }

    override fun showLoading() {
        product_activity_progressBar.visibility = View.VISIBLE
    }

    override fun onSuccess(productList: List<Product>) {
        productsListAdapter = ProductsListAdapter(productList)
        product_recyclerView.adapter = productsListAdapter
        product_activity_progressBar.visibility = View.GONE
    }

    override fun onError(message: String) {
        showToast(message)
        product_activity_progressBar.visibility = View.GONE
    }

    override fun onStop() {
        super.onStop()
        productListPresenter?.disposable()
    }
}