package it.unibo.sc.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.amplifyframework.api.ApiException
import com.amplifyframework.api.graphql.model.ModelQuery
import com.amplifyframework.datastore.generated.model.Product
import com.amplifyframework.kotlin.core.Amplify

/**
 *
 */
class ProductsViewModel : ViewModel() {
    /**
     *
     */
    fun products(): LiveData<List<Product>> = liveData {
        val data = listProducts()
        emit(data)
    }

    /**
     *
     */
    private suspend fun listProducts(): List<Product> {
        return try {
            Amplify.API.query(ModelQuery.list(Product::class.java)).data.items.toList()
        } catch (error: ApiException) {
            Log.e("ProductsViewModel", "Query failed", error)
            emptyList()
        }
    }
}
