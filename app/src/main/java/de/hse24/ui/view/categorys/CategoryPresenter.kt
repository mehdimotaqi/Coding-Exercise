package de.hse24.ui.view.categorys

import de.hse24.data.apiservice.Api
import de.hse24.data.model.Category
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class CategoryPresenter(categoryView: CategoryView) {
    private var categoryView: CategoryView? = null
    private var disposable: Disposable? = null

    init {
        this.categoryView = categoryView
    }

    fun getCategories(){
        categoryView?.showLoading()
        Api.invoke().getCategoryTree()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Category> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onNext(category: Category) {
                    categoryView?.onSuccess(category)
                }

                override fun onError(e: Throwable) {
                    categoryView?.onError(e.localizedMessage!!)
                }

            })
    }

    fun disposable(){
        disposable?.dispose()
    }
}