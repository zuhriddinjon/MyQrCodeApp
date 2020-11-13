package com.example.myqrcodeapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_qr_code.*


private const val ARG_PARAM1 = "param1"

class QrCodeFragment : Fragment() {
    private var qrResult: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            qrResult = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_qr_code, container, false)
        tv_qr_code.text = qrResult
    }

    companion object {
        @JvmStatic
        fun newInstance(qrResult: String) =
            QrCodeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, qrResult)
                }
            }
    }
}