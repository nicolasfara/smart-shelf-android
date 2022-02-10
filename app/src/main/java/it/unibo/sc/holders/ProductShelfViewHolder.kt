package it.unibo.sc.holders

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amplifyframework.datastore.generated.model.ProductShelf
import it.unibo.sc.R

/**
 * A [RecyclerView.ViewHolder] of [ProductShelf].
 */
class ProductShelfViewHolder(view: View, private val context: Context) :
    RecyclerView.ViewHolder(view) {
    private val shelfId: TextView = view.findViewById(R.id.shelfId)
    private val productShelfName: TextView = view.findViewById(R.id.productShelfName)
    private val productCode: TextView = view.findViewById(R.id.productCode)
    private val productLot: TextView = view.findViewById(R.id.productLot)
    private val expiringDate: TextView = view.findViewById(R.id.expiringDate)

    /**
     * Method that bind the UI elements with [ProductShelf] data.
     */
    fun bind(productShelf: ProductShelf?) {
        shelfId.text = context.getString(R.string.shelfId, productShelf?.shelfId ?: "")
        productShelfName.text =
            context.getString(R.string.productShelfName, productShelf?.product?.name ?: "")
        productCode.text =
            context.getString(R.string.productCode, productShelf?.product?.code ?: "")
        productLot.text = context.getString(R.string.productLot, productShelf?.product?.lot ?: "")
        expiringDate.text = context.getString(
            R.string.expiringDate,
            productShelf?.product?.expirationDate?.format() ?: ""
        )
    }
}
