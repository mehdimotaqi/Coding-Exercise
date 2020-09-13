package de.hse24.ui.view.productdetail

import de.hse24.data.apiservice.Api
import de.hse24.data.model.ProductDetailsResponse
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ProductDetailPresenter (productDetailView: ProductDetailView){
    private var productDetailView: ProductDetailView? = null
    private var disposable: Disposable? = null

    init {
        this.productDetailView = productDetailView
    }

    fun getProductDetails(productId: String){
        productDetailView?.showLoading()
        Api.invoke().getProductDetailByProductId(productId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<ProductDetailsResponse>{
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onNext(productDetailsResponse: ProductDetailsResponse) {
                    productDetailView?.onSuccess(productDetailsResponse)
                }

                override fun onError(e: Throwable) {
                    productDetailView?.onError(e.localizedMessage!!)
                }

            })
    }

    fun disposable(){
        disposable?.dispose()
    }
}