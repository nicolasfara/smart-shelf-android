package it.unibo.sc

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.amplifyframework.datastore.generated.model.ProductWarehouse
import it.unibo.sc.queries.ListProductWarehouse

/**
 * Class that loads pages of ProductWarehouse by page token.
 */
class ProductsWarehousePagingSource(private val graphQLData: ListProductWarehouse) :
    PagingSource<String, ProductWarehouse>() {
    override suspend fun load(params: LoadParams<String>): LoadResult<String, ProductWarehouse> {
        val page = params.key
        val response = graphQLData.getProductsWarehouse(page)
        val items = response?.items?.toList() ?: emptyList()
        val nextToken = response?.requestForNextResult?.variables?.get("nextToken")

        Log.d(
            "ProductsPagingSource",
            "page: $page, nextToken: $nextToken, items: ${items.map { it.product.name }}"
        )
        return LoadResult.Page(
            data = items,
            prevKey = null, // Only paging forward.
            nextKey = if (response?.hasNextResult() == true) nextToken.toString() else null
        )
    }

    override fun getRefreshKey(state: PagingState<String, ProductWarehouse>): String? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.nextKey
        }
    }
}
