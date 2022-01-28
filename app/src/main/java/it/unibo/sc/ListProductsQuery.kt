package it.unibo.sc

import android.util.Log
import com.amplifyframework.api.ApiException
import com.amplifyframework.api.aws.ApiGraphQLRequestOptions
import com.amplifyframework.api.aws.AppSyncGraphQLRequest
import com.amplifyframework.api.graphql.GraphQLRequest
import com.amplifyframework.api.graphql.PaginatedResult
import com.amplifyframework.api.graphql.QueryType
import com.amplifyframework.datastore.generated.model.Product
import com.amplifyframework.kotlin.core.Amplify
import com.amplifyframework.util.TypeMaker

/**
 *
 */
class ListProductsQuery() {
    /**
     *
     */
    suspend fun getProducts(nextToken: String?): PaginatedResult<Product>? {
        return listProducts(nextToken, 2)
    }

    private suspend fun listProducts(nextToken: String?, limit: Int): PaginatedResult<Product>? {
        return try {
            val res = Amplify.API.query(getProductsRequest(nextToken, limit))
            Log.d("ListProductsQuery", res.data.items.map { it.name }.toString())

            Log.d("ListProductsQuery", "Query succeeded")
            res.data
        } catch (error: ApiException) {
            Log.e("ListProductsQuery", "Query failed", error)
            null
        }
    }

    private fun getProductsRequest(
        nextToken: String?,
        limit: Int
    ): GraphQLRequest<PaginatedResult<Product>> {
        val responseType = TypeMaker.getParameterizedType(
            PaginatedResult::class.java,
            Product::class.java
        )
        return AppSyncGraphQLRequest.builder()
            .modelClass(Product::class.java)
            .operation(QueryType.LIST)
            .responseType(responseType)
            .variable("limit", "Int", limit)
            .variable("nextToken", "String", nextToken)
            .requestOptions(ApiGraphQLRequestOptions())
            .build()
    }
}
