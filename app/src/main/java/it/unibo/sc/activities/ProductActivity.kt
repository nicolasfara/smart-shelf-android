package it.unibo.sc.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import it.unibo.sc.databinding.ActivityProductBinding

class ProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayProductInfo()

        val writeTagButton = binding.writeTagButton
        val deleteProductButton = binding.deleteProductButton
    }

    private fun displayProductInfo() {
        val productNameText = binding.productInfoName
        val productPriceText = binding.productInfoPrice
        val productExpiringDateText = binding.productInfoExpiringDate

        productNameText.text = intent.extras?.get("productName").toString()
        productPriceText.text = intent.extras?.get("productPrice").toString()
        productExpiringDateText.text = intent.extras?.get("productExpiringDate").toString()
    }
}
