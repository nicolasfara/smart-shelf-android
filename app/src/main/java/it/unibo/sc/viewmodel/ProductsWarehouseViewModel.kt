package it.unibo.sc.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.amplifyframework.datastore.generated.model.ProductWarehouse
import it.unibo.sc.ProductsWarehousePagingSource
import it.unibo.sc.queries.ListProductsWarehouseQuery
import kotlinx.coroutines.flow.Flow

/**
 * Class that store and manage the ProductWarehouse elements.
 */
class ProductsWarehouseViewModel : ViewModel() {
    private companion object {
        const val PAGE_SIZE = 2
    }

    /**
     * This method produces a stream of ProductWarehouse elements.
     *
     * @return A [Flow] that emits a stream of PagingData with [ProductWarehouse] elements.
     */
    fun productsWarehouse() = Pager(PagingConfig(pageSize = PAGE_SIZE)) {
        ProductsWarehousePagingSource(ListProductsWarehouseQuery())
    }.flow
        .cachedIn(viewModelScope)
}
