package it.unibo.sc.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import it.unibo.sc.ListProductsWarehouseQuery
import it.unibo.sc.ProductsWarehousePagingSource

/**
 *
 */
class ProductsWarehouseViewModel : ViewModel() {
    /**
     *
     */
    fun productsWarehouse() = Pager(PagingConfig(pageSize = 2)) {
        ProductsWarehousePagingSource(ListProductsWarehouseQuery())
    }.flow
        .cachedIn(viewModelScope)
}
