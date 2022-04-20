package com.pnuema.android.savestateobserver.app

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.pnuema.android.savestateobserver.OversizeBundleRegistrar
import java.util.*

open class BaseActivity: AppCompatActivity() {

    private lateinit var uuid: UUID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        uuid = OversizeBundleRegistrar.register { stringifyBundle ->
            Log.e(
                "AppBundleWorker",
                "OVERSIZE BUNDLE DETECTED: $stringifyBundle"
            )
        }
    }

    override fun onDestroy() {
        if (::uuid.isInitialized) {
            OversizeBundleRegistrar.unregister(uuid)
        }
        super.onDestroy()
    }
}