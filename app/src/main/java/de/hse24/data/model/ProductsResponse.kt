package de.hse24.data.model

import com.google.gson.annotations.SerializedName

class ProductsResponse(
    @SerializedName("cachingForbidden")
    val cachingForbidden: String,
    @SerializedName("displayName")
    val displayName: String,
    @SerializedName("topShop")
    val topShop: String,
    @SerializedName("resultCount")
    val resultCount: Int,
    @SerializedName("productResults")
    val productResults: List<Product>
)
class Product(
    @SerializedName("sku")
    val sku: String,
    @SerializedName("nameShort")
    val nameShort: String,
    @SerializedName("brandNameLong")
    val brandNameLong: String,
    @SerializedName("productPrice")
    val productPrice: ProductPrice,
    @SerializedName("imageUris")
    val imageUris: List<String>,
    @SerializedName("dimensions")
    val productDimensions: ProductDimensions
)

class ProductPrice(
    @SerializedName("price")
    val price: String,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("percentDiscount")
    val percentDiscount: String
)

class ProductDimensions(
    @SerializedName("COLOR")
    val color: String,
    @SerializedName("SIZE")
    val size: String
)