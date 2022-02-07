package it.unibo.sc.queries

import android.util.Log
import com.amplifyframework.api.ApiException
import com.amplifyframework.api.graphql.model.ModelMutation
import com.amplifyframework.datastore.generated.model.ProductWarehouse
import com.amplifyframework.kotlin.core.Amplify

object ProductWarehouseManager {

    suspend fun decreaseQuantity(
        productWarehouseId: String?,
        currentQuantity: Int?,
        productWarehouseProductId: String?
    ) {
        val productWarehouse = ProductWarehouse.builder()
            .quantity(currentQuantity?.minus(1))
            .productWarehouseProductId(productWarehouseProductId)
            .id(productWarehouseId)
            .build()

        try {
            val res = Amplify.API.mutate(ModelMutation.update(productWarehouse))
            Log.i("ProductWarehouseQuantityUpdate", "Update succeeded $res")
        } catch (error: ApiException) {
            Log.e("ProductWarehouseQuantityUpdate", "Update failed", error)
        }
    }

    suspend fun deleteProductWarehouse(
        productWarehouseId: String?,
        currentQuantity: Int?,
        productWarehouseProductId: String?
    ) {
        val productWarehouse = ProductWarehouse.builder()
            .quantity(currentQuantity)
            .productWarehouseProductId(productWarehouseProductId)
            .id(productWarehouseId)
            .build()

        try {
            val res = Amplify.API.mutate(ModelMutation.delete(productWarehouse))
            Log.i("ProductWarehouseQuantityUpdate", "Update succeeded $res")
        } catch (error: ApiException) {
            Log.e("ProductWarehouseQuantityUpdate", "Update failed", error)
        }
    }
}
