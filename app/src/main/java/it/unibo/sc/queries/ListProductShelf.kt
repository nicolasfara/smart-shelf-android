package it.unibo.sc.queries

import android.util.Log
import com.amplifyframework.api.ApiException
import com.amplifyframework.api.aws.GsonVariablesSerializer
import com.amplifyframework.api.graphql.GraphQLRequest
import com.amplifyframework.api.graphql.PaginatedResult
import com.amplifyframework.api.graphql.SimpleGraphQLRequest
import com.amplifyframework.datastore.generated.model.ProductShelf
import com.amplifyframework.kotlin.core.Amplify
import com.amplifyframework.util.TypeMaker

object ListProductShelf {
    suspend fun getExpiringProductShelf(productId: String): List<ProductShelf>? {

        val document = """
        query listProductShelves {
              listProductShelves(
                filter: {productShelfProductId: {eq: "$productId"}}
              ) {
                items {
                  product {
                    lot
                    code
                    name
                    price
                    promoPrice
                    purchaseDate
                    updatedAt
                    inPromo
                    id
                    expirationDate
                    createdAt
                  }
                  shelfId
                  quantity
                  productShelfProductId
                  updatedAt
                }
                nextToken
                }
        }
        """.trimIndent()
        val responseType = TypeMaker.getParameterizedType(
            PaginatedResult::class.java,
            ProductShelf::class.java
        )
        val query: GraphQLRequest<PaginatedResult<ProductShelf>> = SimpleGraphQLRequest(
            document,
            responseType,
            GsonVariablesSerializer()
        )

        return try {
            val res = Amplify.API.query(query)
            res.data.items.forEach {
                Log.d("ListProductShelf", "res: ${it.shelfId} ${it.product.code} ${it.product.lot}")
            }
            res.data.items.toList()
        } catch (error: ApiException) {
            Log.e("ListProductShelf", "Query failure", error)
            null
        }
    }
}
