package it.unibo.sc.activities

import android.R
import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.MifareClassic
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import it.unibo.sc.databinding.ActivityProductBinding
import it.unibo.sc.queries.ProductWarehouseManager
import it.unibo.sc.utils.NfcOperationsManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

/**
 * Activity that shows product details and allows the corresponding tag writing with nfc.
 *
 */
class ProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductBinding
    private var nfcAdapter: NfcAdapter? = null
    private var intentExtras: Bundle? = null
    private var productCode: String? = null
    private var productLot: Int? = null
    private var productWarehouseId: String? = null
    private var productWarehouseQuantity: Int? = null
    private var productWarehouseProductId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intentExtras = intent.extras

        productCode = intentExtras?.getString("productCode")
        productLot = intentExtras?.getInt("productLot")
        productWarehouseId = intentExtras?.getString("productWarehouseId")
        productWarehouseProductId = intentExtras?.getString("productWarehouseProductId")
        productWarehouseQuantity = intentExtras?.getInt("productWarehouseQuantity")

        displayProductInfo()

        nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        if (nfcAdapter != null && !nfcAdapter?.isEnabled!!) {
            showNFCSettingsDialog()
        }

        val deleteProductButton = binding.deleteProductButton
        deleteProductButton.setOnClickListener {
            lifecycleScope.launch {
                ProductWarehouseManager.deleteProductWarehouse(
                    productWarehouseId,
                    productWarehouseQuantity,
                    productWarehouseProductId
                )
                startProductsActivity()
            }
        }
    }

    override fun onPause() {
        disableForegroundDispatch()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        enableForegroundDispatch()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun startProductsActivity() {
        startActivity(Intent(this, ProductsActivity::class.java))
    }

    private fun enableForegroundDispatch() {
        if (nfcAdapter != null && nfcAdapter?.isEnabled!!) {
            val intent = Intent(this, javaClass).apply {
                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            }
            val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
            val nfcIntentFilter = arrayOf(IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED))
            val techLists = arrayOf(arrayOf(MifareClassic::class.java.name))

            nfcAdapter?.enableForegroundDispatch(this, pendingIntent, nfcIntentFilter, techLists)
        }
    }

    private fun disableForegroundDispatch() {
        nfcAdapter?.disableForegroundDispatch(this)
    }

    private fun displayProductInfo() {
        val productNameText = binding.productInfoName
        val productPriceText = binding.productInfoPrice
        val productExpiringDateText = binding.productInfoExpiringDate

        productNameText.text = intentExtras?.getString("productName")
        productPriceText.text = this.getString(
            it.unibo.sc.R.string.euro,
            intentExtras?.getDouble("productPrice").toString()
        )
        productExpiringDateText.text = intentExtras?.getString("productExpiringDate")
    }

    private fun handleIntent(intent: Intent) {
        val action: String? = intent.action
        if (NfcAdapter.ACTION_TAG_DISCOVERED == action ||
            NfcAdapter.ACTION_TECH_DISCOVERED == action ||
            NfcAdapter.ACTION_NDEF_DISCOVERED == action
        ) {
            val tagFromIntent: Tag? = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG)
            lifecycleScope.launch(Dispatchers.IO) {
                if (verifyTagWriting(productCode, productLot, tagFromIntent)) {
                    ProductWarehouseManager.decreaseQuantity(
                        productWarehouseId,
                        productWarehouseQuantity,
                        productWarehouseProductId
                    )
                }
            }
        }
    }

    private fun verifyTagWriting(productCode: String?, productLot: Int?, tag: Tag?): Boolean {
        return try {
            if (tag != null && productCode != "" && productLot != null) {
                NfcOperationsManager.writeTag(tag, productCode!!, productLot)
                toastNFCMessage("NFC tag written successfully")
                true
            } else {
                Log.e("VerifyTagWriting", "No data to write")
                false
            }
        } catch (e: IOException) {
            Log.d("VerifyTagWriting", e.printStackTrace().toString())
            toastNFCMessage("NFC tag reading error")
            false
        }
    }

    private fun showNFCSettingsDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Enable NFC.")
        builder.setPositiveButton(R.string.ok) { _, _ ->
            val intent = Intent(Settings.ACTION_NFC_SETTINGS)
            startActivity(intent)
        }
        builder.setNegativeButton(R.string.cancel) { _, _ -> finish() }
        builder.create().show()
        return
    }

    private fun toastNFCMessage(text: String) {
        runOnUiThread {
            Toast.makeText(applicationContext, text, Toast.LENGTH_LONG)
                .show()
        }
    }
}
