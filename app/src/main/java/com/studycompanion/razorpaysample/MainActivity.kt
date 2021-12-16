package com.studycompanion.razorpaysample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.razorpay.Checkout
import com.razorpay.PaymentData
import com.razorpay.PaymentResultWithDataListener
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity(), PaymentResultWithDataListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnPay = findViewById<Button>(R.id.btn_pay_rzp)
        btnPay.setOnClickListener {
            openRazorpay()
        }
    }
    init {
    }

    private fun openRazorpay(){
        val checkout = Checkout()

        val options = JSONObject()
        try{
            options.put("name","Something")
            options.put("currency", "INR")
            options.put("amount", "100")
            val method = JSONObject()
            method.put("netbanking", false)
            method.put("card", false)
            method.put("wallet", false)
            method.put("upi", true)
            options.put("method", method)
            val prefill = JSONObject()
            prefill.put("email", "vivekshindhe96@gmail.com")
            prefill.put("contact", "9731585653")
            options.put("prefill", prefill)
        }catch (e: JSONException){
            Log.d("SOMETHING", "${e.printStackTrace()}")
        }
        checkout.setKeyID("rzp_live_OwPnQuHrOcVAax")
        checkout.open(this, options)
    }

    override fun onPaymentSuccess(p0: String?, p1: PaymentData?) {
        try{
            Toast.makeText(this, "$p0 : ${p1.toString()}", Toast.LENGTH_LONG).show()
        }catch (e: Exception){
            Log.d("ERROR", "${e.printStackTrace()}")
        }
    }

    override fun onPaymentError(p0: Int, p1: String?, p2: PaymentData?) {
        try{
            Toast.makeText(this, "$p2 : ${p2.toString()}", Toast.LENGTH_LONG).show()
        }catch (e: Exception){
            Log.d("ERROR", "${e.printStackTrace()}")
        }
    }


}