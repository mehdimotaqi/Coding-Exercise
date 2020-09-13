package de.hse24.data.model

import com.google.gson.annotations.SerializedName

class Category(
    @SerializedName("categoryId")
    val categoryId: String,
    @SerializedName("displayName")
    val displayName: String,
    @SerializedName("resultCount")
    val resultCount: Int,
    @SerializedName("children")
    val categoryChildren: List<Category>
)