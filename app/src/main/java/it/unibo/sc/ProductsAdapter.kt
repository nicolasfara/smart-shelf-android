package it.unibo.sc

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.amplifyframework.datastore.generated.model.Product

/**
 *
 */
class ProductsAdapter(diffCallback: DiffUtil.ItemCallback<Product>, private val context: Context) :
    PagingDataAdapter<Product, ProductViewHolder>(diffCallback) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.product_row, viewGroup, false)
        return ProductViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}
