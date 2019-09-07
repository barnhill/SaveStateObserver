package com.pnuema.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("Integer", 1234)
        outState.putString("String", "StringTest")
        outState.putFloat("Float", 12.34F)

        val innerBundle = Bundle()
        innerBundle.putInt("Integer", 5678)
        innerBundle.putString("String", "InnerStringTest")
        innerBundle.putFloat("Float", 56.78F)

        outState.putBundle("innerBundle", innerBundle)

        super.onSaveInstanceState(outState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
