package it.unibo.sc.queries

import android.util.Log
import com.amplifyframework.api.ApiException
import com.amplifyframework.api.graphql.model.ModelMutation
import com.amplifyframework.datastore.generated.model.ProductWarehouse
import com.amplifyframework.kotlin.core.Amplify

object ProductWarehouseQuantityUpdate {

    suspend fun decreaseQuantity(
        productWarehouseId: String?,
        currentQuantity: Int?,
        productWarehouseProductId: String?
    ) {
        Log.d(
            "CIACIA",
            "c: $productWarehouseId $currentQuantity $productWarehouseProductId"
        )
        val productWarehouse = ProductWarehouse.builder()
            .quantity(currentQuantity?.minus(1))
            .productWarehouseProductId(productWarehouseProductId)
            .id(productWarehouseId)
            .build()
        try {
            Amplify.API.mutate(ModelMutation.update(productWarehouse))
            Log.i("ProductWarehouseQuantityUpdate", "Update succeeded")
        } catch (error: ApiException) {
            Log.e("ProductWarehouseQuantityUpdate", "Update failed", error)
        }
    }
}
