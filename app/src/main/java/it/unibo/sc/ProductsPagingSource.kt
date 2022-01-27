package it.unibo.sc

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.amplifyframework.datastore.generated.model.Product

/**
 *
 */
class ProductsPagingSource(private val graphQLData: ListProductsQuery) :
    PagingSource<String, Product>() {
    override suspend fun load(params: LoadParams<String>): LoadResult<String, Product> {
        return try {
            Log.d("ProductsPagingSource", "cia")

            val page = params.key
            val response = graphQLData.getProducts(page)
            val items = response?.items?.toList() ?: emptyList()
            val nextToken = response?.requestForNextResult?.variables?.get("nextToken")

            Log.d("ProductsPagingSource", response?.items.toString())
            LoadResult.Page(
                data = items,
                prevKey = null, // Only paging forward.
                nextKey = nextToken.toString()
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<String, Product>): String? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.nextKey
        }
    }
}
