package de.hse24.ui.view.productdetail

import de.hse24.data.model.ProductDetailsResponse

interface ProductDetailView {
    fun showLoading()
    fun onError(message: String)
    fun onSuccess(productDetailsResponse: ProductDetailsResponse)

}