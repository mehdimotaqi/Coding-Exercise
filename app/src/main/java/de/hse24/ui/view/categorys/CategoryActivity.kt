package de.hse24.ui.view.categorys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import de.hse24.R
import de.hse24.data.model.Category
import de.hse24.ui.adapters.CategoryAdapter
import de.hse24.ui.showToast
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : AppCompatActivity(), CategoryView {

    private var categoryPresenter: CategoryPresenter? = null
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        linearLayoutManager = LinearLayoutManager(this)
        category_recyclerView.layoutManager = linearLayoutManager
        categoryPresenter = CategoryPresenter(this)
        categoryPresenter?.getCategories()

    }

    override fun showLoading() {
        category_activity_progressBar.visibility = View.VISIBLE
    }


    override fun onError(message: String) {
        showToast(message)
        category_activity_progressBar.visibility = View.GONE
    }

    override fun onSuccess(category: Category) {
        categoryAdapter = CategoryAdapter(category.categoryChildren)
        category_recyclerView.adapter = categoryAdapter
        category_activity_progressBar.visibility = View.GONE
    }

    override fun onStop() {
        super.onStop()
        categoryPresenter?.disposable()
    }
}