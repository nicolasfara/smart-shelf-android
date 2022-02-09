package it.unibo.sc.utils

import android.nfc.Tag
import android.nfc.tech.MifareClassic
import android.util.Log
import java.nio.charset.Charset

/**
 * Object that mange the NFC tag writing.
 */
object NfcOperationsManager {

    /**
     * Writes the NFC tag.
     *
     */
    fun writeTag(tag: Tag, productCode: String, productLot: Int) {
        val mifare = MifareClassic.get(tag)
        mifare.connect()
        for (sectorIndex in 1..2) {
            val auth =
                mifare.authenticateSectorWithKeyA(sectorIndex, MifareClassic.KEY_DEFAULT)
            if (auth) {
                val blockIndex = mifare.sectorToBlock(sectorIndex)
                val data = if (sectorIndex == 1) productCode else productLot.toString()
                mifare.writeBlock(blockIndex, data.toByteArray().copyOf(16))
                readBlock(mifare, blockIndex, sectorIndex)
            } else {
                Log.e("WriteTag", "Sector authentication failed ")
            }
        }
    }

    private fun readBlock(mifare: MifareClassic, blockIndex: Int, sectorIndex: Int) {
        val payload = mifare.readBlock(blockIndex)
        val a = String(payload, Charset.forName("US-ASCII"))
        Log.d(
            "ReadJustWrittenBlock",
            "sectorIndex: $sectorIndex blockIndex: $blockIndex blockData: $a"
        )
    }
}
