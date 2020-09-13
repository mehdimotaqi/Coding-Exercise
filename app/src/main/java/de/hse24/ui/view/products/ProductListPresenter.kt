package de.hse24.ui.view.products

import android.util.Log
import de.hse24.data.apiservice.Api
import de.hse24.data.model.ProductsResponse
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ProductListPresenter(productListV: ProductListView) {

    private var productListView: ProductListView = productListV
    private var disposable: Disposable? = null

    fun getProductList(categoryId: String){
        productListView.showLoading()
         Api.invoke().getProductsByCategoryId(categoryId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<ProductsResponse>{
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onNext(productsResponse: ProductsResponse) {
                    productListView.onSuccess(productsResponse.productResults)
                }

                override fun onError(e: Throwable) {
                   productListView.onError(e.localizedMessage!!)
                }

            })
    }

    fun disposable(){
        disposable?.dispose()
    }
}