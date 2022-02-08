package it.unibo.sc.viewmodel

import androidx.lifecycle.ViewModel
import com.amplifyframework.datastore.generated.model.ProductShelf
import it.unibo.sc.queries.ListProduct
import it.unibo.sc.queries.ListProductShelf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Class that store and manage the ProductShelf elements.
 */
class ProductsShelfViewModel : ViewModel() {
    /**
     * This method produces a stream of ProductShelf elements.
     *
     * @return A [Flow] that emits a stream of List with [ProductShelf] elements.
     */
    fun productShelves(): Flow<List<ProductShelf>> = flow {
        emit(loadProductShelves())
    }

    private suspend fun loadProductShelves(): List<ProductShelf> {
        return ListProduct.products().flatMap { id ->
            ListProductShelf.getExpiringProductShelf(id) ?: emptyList()
        }
    }
}
