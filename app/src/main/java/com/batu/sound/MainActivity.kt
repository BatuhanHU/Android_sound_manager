package com.batu.sound

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val TAG = "Main Activity"
    val REQUEST_LOCATION = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_search.setOnClickListener {
            checkPermission()
        }

    }

    private fun checkPermission(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            // Should we show an explanation?
            ActivityCompat.requestPermissions(this, arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION
            ), REQUEST_LOCATION)
        }
        else{
            search()
        }
    }

    private fun search(){
        val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

        Log.d(TAG, "Connected SSID : ${wifiManager.connectionInfo.ssid}\n")

        val list = wifiManager.configuredNetworks
        Log.d(TAG, "Scan count ${list.size}")
        for(wifi in list){
            Log.d(TAG, wifi.SSID)
        }
    }
}
