package de.hse24.ui.view.products

import de.hse24.data.model.Product

interface ProductListView {
    fun showLoading()
    fun onSuccess(productList: List<Product>)
    fun onError(message: String)
}