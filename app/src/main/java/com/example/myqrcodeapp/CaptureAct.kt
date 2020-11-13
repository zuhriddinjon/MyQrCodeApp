package com.example.myqrcodeapp

import com.journeyapps.barcodescanner.CaptureActivity

class CaptureAct : CaptureActivity() {

    override fun onBackPressed() {
        finishAffinity()
//        super.onBackPressed()
    }
}