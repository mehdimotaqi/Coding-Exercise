package de.hse24.ui.adapters

import android.content.Intent
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import de.hse24.R
import de.hse24.data.model.Product
import de.hse24.ui.concatenateImageUri
import de.hse24.ui.inflate
import de.hse24.ui.view.productdetail.ProductDetailActivity
import de.hse24.ui.view.products.ProductListActivity
import kotlinx.android.synthetic.main.product_recyclerview_item_row.view.*

class ProductsListAdapter(private val productsList: List<Product>) : RecyclerView.Adapter<ProductsListAdapter.ProductsListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsListViewHolder {
        val inflaterView = parent.inflate(R.layout.product_recyclerview_item_row, false)
        return ProductsListViewHolder(inflaterView)
    }

    override fun getItemCount() = productsList.size

    override fun onBindViewHolder(holder: ProductsListViewHolder, position: Int) {
        val itemProduct = productsList[position]
        holder.bindProducts(itemProduct)
    }

    class ProductsListViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        private var view : View = v
        private var product: Product? = null

        companion object {
            //Add a key for easy reference to the item launching the RecyclerView.
            private val PRODUCT_ID = "PRODUCTID"
        }

        fun bindProducts(product: Product){
            this.product = product
            product.let {
                Picasso.get().load(concatenateImageUri(it.imageUris[0])).into(view.product_img)
                view.product_name.text = it.nameShort
                view.product_price.text = it.productPrice.price + " " + it.productPrice.currency
                view.product_discount.text = it.productPrice.percentDiscount + "%"

            }

            view.apply {
                this.setOnClickListener {
                    val context = itemView.context
                    val showProductDetailIntent = Intent(context, ProductDetailActivity::class.java)
                    showProductDetailIntent.putExtra(PRODUCT_ID, product.sku)
                    context.startActivity(showProductDetailIntent)
                }
            }
        }
    }
}