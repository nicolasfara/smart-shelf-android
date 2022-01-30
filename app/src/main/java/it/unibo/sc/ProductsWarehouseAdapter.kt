package it.unibo.sc

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.amplifyframework.datastore.generated.model.ProductWarehouse

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
        holder.bind(item)
    }
}
