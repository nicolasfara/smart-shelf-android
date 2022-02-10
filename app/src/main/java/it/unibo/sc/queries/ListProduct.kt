package it.unibo.sc.queries

import android.util.Log
import com.amplifyframework.api.ApiException
import com.amplifyframework.api.graphql.model.ModelQuery
import com.amplifyframework.datastore.generated.model.Product
import com.amplifyframework.kotlin.core.Amplify
import java.time.LocalDateTime

/**
 * GraphQL API.
 */
object ListProduct {
    private const val DAYS_TO_SUM = 2L

    /**
     * GraphQL API that gets all the Products.
     *
     */
    suspend fun products(): List<String> {
        return try {
            val expDate = LocalDateTime.now().plusDays(DAYS_TO_SUM).toString()
            val condition = Product.EXPIRATION_DATE.le(expDate.split("T")[0])
            val res = Amplify.API.query(ModelQuery.list(Product::class.java, condition))
            Log.d("ListProduct", "res: ${res.data.items.map { it.id }}")
            res.data.items.map { it.id }
        } catch (error: ApiException) {
            Log.e("ListProduct", "Query failure", error)
            emptyList()
        }
    }
}
