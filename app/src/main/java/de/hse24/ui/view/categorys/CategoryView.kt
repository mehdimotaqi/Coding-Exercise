package de.hse24.ui.view.categorys

import de.hse24.data.model.Category

interface CategoryView {
    fun showLoading()
    fun onError(message: String)
    fun onSuccess(category: Category)
}