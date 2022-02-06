package it.unibo.sc

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.amplifyframework.datastore.generated.model.Product
import com.amplifyframework.datastore.generated.model.ProductWarehouse
import it.unibo.sc.activities.ProductActivity

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
            startProductActivity(item?.product)
        }
        holder.bind(item)
    }

    private fun startProductActivity(product: Product?) {
        val intent = Intent(context, ProductActivity::class.java)
        intent.putExtra("productName", product?.name ?: "")
        intent.putExtra("productPrice", product?.price ?: "")
        intent.putExtra("productExpiringDate", product?.expirationDate?.format() ?: "")
        intent.putExtra("productCode", product?.code ?: "")
        intent.putExtra("productLot", product?.lot ?: "")
        context.startActivity(intent)
    }
}
