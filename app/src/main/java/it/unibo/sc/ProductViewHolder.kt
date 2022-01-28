package it.unibo.sc

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amplifyframework.datastore.generated.model.Product

/**
 *
 */
class ProductViewHolder(view: View, private val context: Context) : RecyclerView.ViewHolder(view) {
    private val productName: TextView = view.findViewById(R.id.productName)
    private val productPrice: TextView = view.findViewById(R.id.productPrice)
    //    private val linearLayout: LinearLayout = view.findViewById(R.id.productLayout)

    /**
     *
     */
    fun bind(product: Product?) {
        productName.text = product?.name
        productPrice.text = context.getString(R.string.euro, product?.price.toString())
    }
}
