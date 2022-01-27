package it.unibo.sc.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.amplifyframework.datastore.generated.model.Product
import it.unibo.sc.ListProductsQuery
import it.unibo.sc.ProductsPagingSource

/**
 *
 */
class ProductsViewModel : ViewModel() {
    private val listProductsQuery = ListProductsQuery()
    /**
     *
     */
    fun products(): LiveData<PagingData<Product>> = Pager(PagingConfig(pageSize = 5)) {
        ProductsPagingSource(listProductsQuery)
    }.liveData
}
