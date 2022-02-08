package it.unibo.sc.holders

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amplifyframework.datastore.generated.model.ProductWarehouse
import it.unibo.sc.R

/**
 * A [RecyclerView.ViewHolder] of [ProductWarehouse].
 */
class ProductWarehouseViewHolder(view: View, private val context: Context) :
    RecyclerView.ViewHolder(view) {
    private val productName: TextView = view.findViewById(R.id.productName)
    private val productPrice: TextView = view.findViewById(R.id.productPrice)

    /**
     * ProductLayout.
     */
    val productLayout: LinearLayout = view.findViewById(R.id.productLayout)

    /**
     * Method that bind the UI elements with [ProductWarehouse] data.
     */
    fun bind(productWarehouse: ProductWarehouse?) {
        productName.text = productWarehouse?.product?.name ?: ""
        productPrice.text =
            context.getString(R.string.quantity, productWarehouse?.quantity ?: "")
    }
}
