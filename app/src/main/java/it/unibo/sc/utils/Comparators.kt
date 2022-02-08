package it.unibo.sc.utils

import androidx.recyclerview.widget.DiffUtil
import com.amplifyframework.datastore.generated.model.ProductShelf
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

/**
 * List of ProductShelf comparator.
 */
object ProductShelfComparator : DiffUtil.ItemCallback<ProductShelf>() {
    override fun areItemsTheSame(oldItem: ProductShelf, newItem: ProductShelf): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ProductShelf, newItem: ProductShelf): Boolean {
        return oldItem == newItem
    }
}
