package de.hse24.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import de.hse24.data.model.ProductBasket

@Dao
interface ProductBasketDAO {
    @Query("SELECT * from product_basket_table")
    fun getAllProductsInTheBasket(): LiveData<List<ProductBasket>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(productBasket: ProductBasket)

    @Delete
    suspend fun delete(productBasket: ProductBasket)
}