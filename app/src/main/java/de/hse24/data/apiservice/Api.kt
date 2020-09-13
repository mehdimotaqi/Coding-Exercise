package de.hse24.data.apiservice

import de.hse24.data.model.Category
import de.hse24.data.model.ProductDetailsResponse
import de.hse24.data.model.ProductsResponse
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://www.hse24.de/ext-api/app/1/"


interface Api {

    @GET("category/tree")
    fun getCategoryTree(): Observable<Category>

    @GET("c/**/*-{categoryID}")
    fun getProductsByCategoryId(@Path("categoryID") categoryID: String): Observable<ProductsResponse>

    @GET("product/{productId}")
    fun getProductDetailByProductId(@Path("productId") productId: String): Observable<ProductDetailsResponse>

    companion object{
        operator fun invoke(): Api {
            val headerInterceptor = Interceptor{chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .addHeader("Accept","application/json")
                    .addHeader("appDevice","ANDROID_PHONE")
                    .addHeader("locale","de_DE")

                val request = requestBuilder.build()
                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(headerInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(Api::class.java)
        }


    }
}