package it.unibo.sc.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import it.unibo.sc.ListProductsQuery
import it.unibo.sc.ProductsPagingSource

/**
 *
 */
class ProductsViewModel : ViewModel() {
    /**
     *
     */
    fun products() = Pager(PagingConfig(pageSize = 2)) {
        ProductsPagingSource(ListProductsQuery())
    }.flow
        .cachedIn(viewModelScope)
}
