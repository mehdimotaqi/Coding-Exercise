package de.hse24.ui.adapters

import android.content.Intent
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import de.hse24.R
import de.hse24.data.model.Category
import de.hse24.ui.inflate
import de.hse24.ui.view.products.ProductListActivity
import kotlinx.android.synthetic.main.category_recyclerview_item_row.view.*
import kotlin.math.log

class CategoryAdapter(private val categories: List<Category>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val inflatedView = parent.inflate(R.layout.category_recyclerview_item_row, false)
        return CategoryHolder(inflatedView)
    }

    override fun getItemCount() = categories.size

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        val itemCategory = categories[position]
        holder.bindCategory(itemCategory)
    }

    class CategoryHolder(v: View) : RecyclerView.ViewHolder(v){

        private var view: View = v
        private var category: Category? = null

        companion object {
            //Add a key for easy reference to the item launching the RecyclerView.
            private val CATEGORY_ID = "CATEGORYID"
        }

        fun bindCategory(category: Category) {
            this.category = category
            category.displayName.let {
                view.category_item_btn.text = it
            }
            view.category_item_btn.apply {
                this.setOnClickListener {
                    val context = itemView.context
                    val showProductListIntent = Intent(context, ProductListActivity::class.java)
                    showProductListIntent.putExtra(CATEGORY_ID, category.categoryId)
                    context.startActivity(showProductListIntent)
                }
            }
        }
    }
}