package de.hse24.ui.view.Basket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import de.hse24.R
import de.hse24.data.model.ProductBasket
import de.hse24.ui.adapters.ProductBasketAdapter
import de.hse24.ui.showToast
import kotlinx.android.synthetic.main.activity_basket.*

class ProductBasketActivity : AppCompatActivity(),
    ProductBasketAdapter.OnProductBasketClickListener {

    private lateinit var productBasketViewModel: ProductBasketViewModel
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var productBasketAdapter: ProductBasketAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basket)
        gridLayoutManager = GridLayoutManager(this, 2)
        productBasketViewModel = ViewModelProvider(this).get(ProductBasketViewModel::class.java)
        product_basket_recyclerView.layoutManager = gridLayoutManager

        productBasketViewModel.allProductInTheBasket?.observe(this, Observer {
            if (it.isNotEmpty()) {
                productBasketAdapter = ProductBasketAdapter(it)
                product_basket_recyclerView.adapter = productBasketAdapter
                productBasketAdapter.onProductBasketClickListener = this
            }
        })
    }
    override fun onClickDeleteProduct(productBasket: ProductBasket) {
        productBasketViewModel.delete(productBasket)
    }
}