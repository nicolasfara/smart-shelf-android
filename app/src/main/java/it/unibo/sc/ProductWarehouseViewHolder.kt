package it.unibo.sc

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amplifyframework.datastore.generated.model.ProductWarehouse

/**
 * A [RecyclerView.ViewHolder] of [ProductWarehouse].
 */
class ProductWarehouseViewHolder(view: View, private val context: Context) :
    RecyclerView.ViewHolder(view) {
    private val productName: TextView = view.findViewById(R.id.productName)
    private val productPrice: TextView = view.findViewById(R.id.productPrice)

    /**
     * Method that bind the UI elements with [ProductWarehouse] data.
     */
    fun bind(productWarehouse: ProductWarehouse?) {
        productName.text = productWarehouse?.product?.name
        productPrice.text =
            context.getString(R.string.euro, productWarehouse?.product?.price.toString())
    }
}
