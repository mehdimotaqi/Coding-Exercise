package de.hse24.ui.view.Basket

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import de.hse24.data.Repositories.ProductBasketRepository
import de.hse24.data.dao.ProductBasketDAO
import de.hse24.data.model.ProductBasket
import de.hse24.data.roomdatabase.ProductBasketRoomDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductBasketViewModel(application: Application): AndroidViewModel(application) {
    private var  repository: ProductBasketRepository

    var allProductInTheBasket: LiveData<List<ProductBasket>>? = null
    init {
        val productBasketDAO = ProductBasketRoomDataBase.getDataBase(application).productBasketDao()
        repository = ProductBasketRepository(productBasketDAO)
        allProductInTheBasket = repository.allProductsInTheBasket
    }

    fun insert(productBasket: ProductBasket) =
        viewModelScope.launch(Dispatchers.IO){
            repository.insert(productBasket)
        }

    fun delete(productBasket: ProductBasket) =
        viewModelScope.launch(Dispatchers.IO){
            repository.delete(productBasket)
        }
}