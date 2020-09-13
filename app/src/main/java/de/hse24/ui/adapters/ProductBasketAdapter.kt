package de.hse24.ui.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import de.hse24.R
import de.hse24.data.model.ProductBasket
import de.hse24.ui.concatenateImageUri
import de.hse24.ui.inflate
import kotlinx.android.synthetic.main.product_basket_recyclerview_item_row.view.*

class ProductBasketAdapter(private val productBaskets: List<ProductBasket>) :
    RecyclerView.Adapter<ProductBasketAdapter.ProductBasketHolder>() {

    var onProductBasketClickListener: OnProductBasketClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductBasketHolder {
        val inflater = parent.inflate(R.layout.product_basket_recyclerview_item_row, false)
        return ProductBasketHolder(inflater)
    }

    override fun getItemCount() = productBaskets.size

    override fun onBindViewHolder(holder: ProductBasketHolder, position: Int) {
        val currentItem = productBaskets[position]
        holder.binProductBasket(currentItem)
        holder.itemView.product_basket_delete_btn.setOnClickListener {
            onProductBasketClickListener?.onClickDeleteProduct(currentItem)
        }
    }

    class ProductBasketHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        private var productBasket: ProductBasket? = null


        fun binProductBasket(productBasket: ProductBasket) {
            this.productBasket = productBasket
            productBasket.apply {
                Picasso.get().load(concatenateImageUri(this.productImage))
                    .into(view.product_basket_img)
                view.product_basket_name.text = this.productName
                view.product_basket_price.text = this.productPrice + " " + this.productCurrency
            }


        }
    }

    interface OnProductBasketClickListener {
        fun onClickDeleteProduct(productBasket: ProductBasket)
    }

}