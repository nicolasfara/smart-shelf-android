package it.unibo.sc.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.amplifyframework.datastore.generated.model.ProductWarehouse
import it.unibo.sc.R
import it.unibo.sc.activities.ProductActivity
import it.unibo.sc.holders.ProductWarehouseViewHolder

/**
 * A [PagingDataAdapter] of [ProductWarehouse].
 */
class ProductsWarehouseAdapter(
    diffCallback: DiffUtil.ItemCallback<ProductWarehouse>,
    private val context: Context
) :
    PagingDataAdapter<ProductWarehouse, ProductWarehouseViewHolder>(diffCallback) {

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ProductWarehouseViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.product_row, viewGroup, false)
        return ProductWarehouseViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: ProductWarehouseViewHolder, position: Int) {
        val item = getItem(position)
        holder.productLayout.setOnClickListener {
            startProductActivity(item)
        }
        holder.bind(item)
    }

    private fun startProductActivity(productWarehouse: ProductWarehouse?) {
        val intent = Intent(context, ProductActivity::class.java)
        intent.putExtra("productWarehouseId", productWarehouse?.id)
        intent.putExtra("productWarehouseQuantity", productWarehouse?.quantity)
        intent.putExtra("productWarehouseProductId", productWarehouse?.productWarehouseProductId)
        intent.putExtra("productName", productWarehouse?.product?.name ?: "")
        intent.putExtra("productPrice", productWarehouse?.product?.price ?: "")
        intent.putExtra(
            "productExpiringDate",
            productWarehouse?.product?.expirationDate?.format() ?: ""
        )
        intent.putExtra("productCode", productWarehouse?.product?.code ?: "")
        intent.putExtra("productLot", productWarehouse?.product?.lot)
        context.startActivity(intent)
    }
}
