package it.unibo.sc.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.amplifyframework.datastore.generated.model.ProductShelf
import it.unibo.sc.R
import it.unibo.sc.holders.ProductShelfViewHolder

/**
 * A [ListAdapter] of [ProductShelf].
 */
class ProductShelfAdapter(
    diffCallback: DiffUtil.ItemCallback<ProductShelf>,
    private val context: Context
) :
    ListAdapter<ProductShelf, ProductShelfViewHolder>(diffCallback) {
    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ProductShelfViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.notification_row, viewGroup, false)
        return ProductShelfViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: ProductShelfViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}
