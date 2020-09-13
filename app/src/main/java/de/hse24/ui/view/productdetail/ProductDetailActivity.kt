package de.hse24.ui.view.productdetail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import de.hse24.R
import de.hse24.data.model.ProductBasket
import de.hse24.data.model.ProductDetailsResponse
import de.hse24.ui.concatenateImageUri
import de.hse24.ui.showToast
import de.hse24.ui.view.Basket.ProductBasketActivity
import de.hse24.ui.view.Basket.ProductBasketViewModel
import kotlinx.android.synthetic.main.activity_product_detail.*
import kotlinx.coroutines.Job

class ProductDetailActivity : AppCompatActivity(), ProductDetailView {

    private var productDetailPresenter: ProductDetailPresenter? = null
    private var productId: String? = null
    private lateinit var productBasketViewModel: ProductBasketViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        productBasketViewModel = ViewModelProvider(this).get(ProductBasketViewModel::class.java)
        productDetailPresenter = ProductDetailPresenter(this)
        productId = intent.getStringExtra("PRODUCTID")
        productDetailPresenter?.getProductDetails(productId!!)

        to_productBasket_btn.setOnClickListener {
            val intent: Intent = Intent(this, ProductBasketActivity::class.java)
            startActivity(intent)
        }
    }

    override fun showLoading() {
        product_detail_progressBar.visibility = View.VISIBLE
    }

    override fun onError(message: String) {
        showToast(message)
        product_detail_progressBar.visibility = View.GONE
    }

    override fun onSuccess(productDetailsResponse: ProductDetailsResponse) {
        Picasso.get().load(concatenateImageUri(productDetailsResponse.imageUris[0]))
            .into(product_detail_img)
        product_detail_name.text = productDetailsResponse.nameShort
        product_detail_price.text = productDetailsResponse.productPrice.price +" "+
                productDetailsResponse.productPrice.currency;
        ratingBar.rating = productDetailsResponse.averageStars
        reviews.text = productDetailsResponse.reviewers.toString()
        product_detail_progressBar.visibility = View.GONE

        add_to_cart_btn.setOnClickListener {
            productBasketViewModel.insert(
                ProductBasket(productId!!,
                    productDetailsResponse.nameShort,
                    productDetailsResponse.productPrice.price,
                    productDetailsResponse.imageUris[0],
                    productDetailsResponse.productPrice.currency)
            )
        }
    }

    override fun onStop() {
        super.onStop()
        productDetailPresenter?.disposable()
    }
}