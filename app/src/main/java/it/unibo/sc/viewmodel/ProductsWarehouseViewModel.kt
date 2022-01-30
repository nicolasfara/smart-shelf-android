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
    /**
     * This method produces a stream of ProductWarehouse elements.
     *
     * @return A [Flow] that emits a stream of PagingData with [ProductWarehouse] elements.
     */
    fun productsWarehouse() = Pager(PagingConfig(pageSize = 2)) {
        ProductsWarehousePagingSource(ListProductsWarehouseQuery())
    }.flow
        .cachedIn(viewModelScope)
}
