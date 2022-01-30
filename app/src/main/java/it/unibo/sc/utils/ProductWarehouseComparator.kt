package it.unibo.sc.utils

import androidx.recyclerview.widget.DiffUtil
import com.amplifyframework.datastore.generated.model.ProductWarehouse

/**
 * Pagination of ProductWarehouse comparator.
 */
object ProductWarehouseComparator : DiffUtil.ItemCallback<ProductWarehouse>() {
    override fun areItemsTheSame(oldItem: ProductWarehouse, newItem: ProductWarehouse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ProductWarehouse, newItem: ProductWarehouse): Boolean {
        return oldItem == newItem
    }
}
