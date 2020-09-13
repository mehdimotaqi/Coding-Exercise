package de.hse24.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_basket_table")
data class ProductBasket(
    @PrimaryKey @ColumnInfo(name = "product_sku")
    val productSKU: String,
    @ColumnInfo(name = "product_name")
    val productName: String,
    @ColumnInfo(name = "product_price")
    val productPrice: String,
    @ColumnInfo(name = "product_image")
    val productImage: String,
    @ColumnInfo(name = "product_currency")
    val productCurrency: String
)