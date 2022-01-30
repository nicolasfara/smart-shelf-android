package it.unibo.sc.queries

import android.util.Log
import com.amplifyframework.api.ApiException
import com.amplifyframework.api.aws.ApiGraphQLRequestOptions
import com.amplifyframework.api.aws.AppSyncGraphQLRequest
import com.amplifyframework.api.graphql.GraphQLRequest
import com.amplifyframework.api.graphql.PaginatedResult
import com.amplifyframework.api.graphql.QueryType
import com.amplifyframework.datastore.generated.model.ProductWarehouse
import com.amplifyframework.kotlin.core.Amplify
import com.amplifyframework.util.TypeMaker

/**
 * GraphQL API.
 */
class ListProductsWarehouseQuery() {
    /**
     * This method fetches all the ProductWarehouse elements from the database.
     *
     * @param nextToken the GraphQL token that indicates the next page to retrieve.
     */
    suspend fun getProductsWarehouse(nextToken: String?): PaginatedResult<ProductWarehouse>? {
        return listProductsWarehouse(nextToken, 2)
    }

    private suspend fun listProductsWarehouse(
        nextToken: String?,
        limit: Int?
    ): PaginatedResult<ProductWarehouse>? {
        return try {
            val res = Amplify.API.query(productsWarehouseRequest(nextToken, limit))
            Log.d(
                "ListProductsQuery",
                "Query succeeded ${res.data.items.map { it.product.name }}"
            )
            res.data
        } catch (error: ApiException) {
            Log.e("ListProductsQuery", "Query failed", error)
            null
        }
    }

    private fun productsWarehouseRequest(
        nextToken: String?,
        limit: Int?
    ): GraphQLRequest<PaginatedResult<ProductWarehouse>> {
        val responseType = TypeMaker.getParameterizedType(
            PaginatedResult::class.java,
            ProductWarehouse::class.java
        )
        return AppSyncGraphQLRequest.builder()
            .modelClass(ProductWarehouse::class.java)
            .operation(QueryType.LIST)
            .responseType(responseType)
            .variable("limit", "Int", limit)
            .variable("nextToken", "String", nextToken)
            .requestOptions(ApiGraphQLRequestOptions())
            .build()
    }
}
