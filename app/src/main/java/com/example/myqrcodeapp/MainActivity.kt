package com.example.myqrcodeapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.client.android.Intents
import com.google.zxing.integration.android.IntentIntegrator


private const val CUSTOMIZED_REQUEST_CODE = 0x0000ffff

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            scanCode()
//            finish()
        }, 500)

//        scanCode()
    }

    private fun scanCode() {
        IntentIntegrator(this).apply {
            captureActivity = CaptureAct::class.java
            setOrientationLocked(false)
            setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
            setPrompt("Scanning Code")
            initiateScan()
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode != CUSTOMIZED_REQUEST_CODE && requestCode != IntentIntegrator.REQUEST_CODE) {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data)
            return
        }
        when (requestCode) {
            CUSTOMIZED_REQUEST_CODE -> {
                Toast.makeText(this, "REQUEST_CODE = $requestCode", Toast.LENGTH_LONG).show()
            }
            else -> {
            }
        }
        val result = IntentIntegrator.parseActivityResult(resultCode, data)
        if (result.contents == null) {
            val originalIntent = result.originalIntent
            if (originalIntent == null) {
                showDialog("Cancelled scan")
                Log.d("MainActivity", "Cancelled scan")
            } else if (originalIntent.hasExtra(Intents.Scan.MISSING_CAMERA_PERMISSION)) {
                showDialog("Cancelled scan due to missing camera permission")
                Log.d("MainActivity", "Cancelled scan due to missing camera permission")
            }
        } else {
            showDialog(result.contents)
            Log.d("MainActivity", "Scanned")
        }
    }

    private fun showDialog(s: String) {
        AlertDialog.Builder(this)
            .setMessage(s)
            .setTitle("Scanning Result")
            .setPositiveButton("scan again") { _, _ -> scanCode() }
            .setNegativeButton("finish") { _, _ -> finish() }
            .create()
            .show()
    }

}