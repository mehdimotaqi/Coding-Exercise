package de.hse24.data.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import de.hse24.data.dao.ProductBasketDAO
import de.hse24.data.model.ProductBasket

@Database(entities = [ProductBasket::class], version = 3, exportSchema = false)
public abstract class ProductBasketRoomDataBase : RoomDatabase() {

    abstract fun productBasketDao(): ProductBasketDAO

    companion object{
        @Volatile
        private var INSTANCE: ProductBasketRoomDataBase? = null

        fun getDataBase(context: Context): ProductBasketRoomDataBase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductBasketRoomDataBase::class.java,
                   "product_basket_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}