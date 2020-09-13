package de.hse24.ui

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes

const val PRODUCT_IMAGE_BASE_URL = "https://pic.hse24-dach.net/media/de/products/"
const val IMAGE_SIZE_SUFFIX = "pics480.jpg"

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun Activity.showToast(message: String){
    return Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun concatenateImageUri(imageUri: String): String{
    return PRODUCT_IMAGE_BASE_URL + imageUri.trim() + IMAGE_SIZE_SUFFIX
}