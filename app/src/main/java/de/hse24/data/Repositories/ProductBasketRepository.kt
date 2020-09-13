package de.hse24.data.Repositories

import androidx.lifecycle.LiveData
import de.hse24.data.dao.ProductBasketDAO
import de.hse24.data.model.ProductBasket

class ProductBasketRepository(private val productBasketDAO: ProductBasketDAO) {

    val allProductsInTheBasket: LiveData<List<ProductBasket>> = productBasketDAO.getAllProductsInTheBasket()

    suspend fun insert(productBasket: ProductBasket){
        productBasketDAO.insert(productBasket)
    }

    suspend fun delete(productBasket: ProductBasket){
        productBasketDAO.delete(productBasket)
    }
}