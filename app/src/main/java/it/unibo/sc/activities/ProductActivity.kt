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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.nio.charset.Charset

class ProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductBinding
    private var nfcAdapter: NfcAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("ON", "onCreate")

        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayProductInfo()

        nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        if (nfcAdapter != null && !nfcAdapter?.isEnabled!!) {
            showWirelessSettingsDialog()
        }

        val deleteProductButton = binding.deleteProductButton
    }

    override fun onPause() {
        disableForegroundDispatch()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        startForegroundDispatch()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun startForegroundDispatch() {
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

        productNameText.text = intent.extras?.get("productName").toString()
        productPriceText.text = intent.extras?.get("productPrice").toString()
        productExpiringDateText.text = intent.extras?.get("productExpiringDate").toString()
    }

    private fun handleIntent(intent: Intent) {
        val action: String? = intent.action
        if (NfcAdapter.ACTION_TAG_DISCOVERED == action ||
            NfcAdapter.ACTION_TECH_DISCOVERED == action ||
            NfcAdapter.ACTION_NDEF_DISCOVERED == action
        ) {
            val tagFromIntent: Tag? = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG)
            lifecycleScope.launch(Dispatchers.IO) {
                writeTag(tagFromIntent)
            }
        }
    }

    private fun writeTag(tag: Tag?) {
        val productCode = intent.extras?.get("productCode").toString()
        val productLot = intent.extras?.get("productLot").toString()
        if (productCode != "" && productLot != "") {
            MifareClassic.get(tag)?.use { mifare ->
                mifare.connect()
                try {
                    for (sectorIndex in 1..2) {
                        val auth = mifare.authenticateSectorWithKeyA(
                            sectorIndex,
                            MifareClassic.KEY_DEFAULT
                        )
                        if (auth) {
                            val blockIndex = mifare.sectorToBlock(sectorIndex)
                            val data = if (sectorIndex == 1) productCode else productLot
                            mifare.writeBlock(blockIndex, data.toByteArray().copyOf(16))
                            val payload = mifare.readBlock(blockIndex)
                            val a = String(payload, Charset.forName("US-ASCII"))
                            Log.d(
                                "WriteTag",
                                "sectorIndex: $sectorIndex blockIndex: $blockIndex blockData: $a"
                            )
                        } else {
                            Log.e("WriteTag", "Sector authentication failed ")
                        }
                    }
                    toastNFCMessage("NFC tag written successfully")
                } catch (e: IOException) {
                    Log.d("WriteTag", e.printStackTrace().toString())
                    toastNFCMessage("NFC tag reading error")
                }
            }
        } else {
            Log.e("WriteTag", "No data to write")
        }
    }

    private fun showWirelessSettingsDialog() {
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
