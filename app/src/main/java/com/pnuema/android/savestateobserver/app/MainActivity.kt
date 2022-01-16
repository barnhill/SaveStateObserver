package com.pnuema.android.savestateobserver.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import com.pnuema.android.savestateobserver.OversizeBundleNotifier
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var uuid: UUID

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

        uuid = OversizeBundleNotifier.register(
            OneTimeWorkRequest.Builder(OversizedBundleWorker::class.java)
                .setConstraints(
                    Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build()
                )
        )
    }

    override fun onDestroy() {
        if (::uuid.isInitialized) {
            OversizeBundleNotifier.unregister(uuid)
        }
        super.onDestroy()
    }
}
