package com.pnuema.android.savestateobserver.app

import android.os.Bundle
import java.util.*

class MainActivity : BaseActivity() {
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("Integer", 1234)
        outState.putString("String", "StringTest")
        outState.putFloat("Float", 12.34F)

        val innerBundle = Bundle()
        innerBundle.putInt("Integer", 5678)
        innerBundle.putString("String", "InnerStringTest")
        innerBundle.putFloat("Float", 56.78F)

        //generate 50k of data for the bundle
        var bigString = ""
        while (bigString.length < 50000) {
            bigString += UUID.randomUUID().toString()
        }
        innerBundle.putString("BigString", bigString)

        outState.putBundle("innerBundle", innerBundle)

        super.onSaveInstanceState(outState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
