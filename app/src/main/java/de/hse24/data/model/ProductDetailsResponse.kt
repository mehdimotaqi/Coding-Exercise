package de.hse24.data.model

import com.google.gson.annotations.SerializedName

class ProductDetailsResponse(
    @SerializedName("title")
    val title: String,
    @SerializedName("nameShort")
    val nameShort: String,
    @SerializedName("longDescription")
    val longDescription: String,
    @SerializedName("averageStars")
    val averageStars: Float,
    @SerializedName("reviewers")
    val reviewers: Int,
    @SerializedName("productPrice")
    val productPrice: ProductPrice,
    @SerializedName("imageUris")
    val imageUris: List<String>
)