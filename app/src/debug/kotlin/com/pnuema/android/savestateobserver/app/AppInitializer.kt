package com.pnuema.android.savestateobserver.app

import android.content.Context
import android.util.Log
import androidx.startup.Initializer
import com.pnuema.android.savestateobserver.OversizeBundleRegistrar

@Suppress("unused") // startup-runtime initializer
class AppInitializer: Initializer<Unit> {
    override fun create(context: Context) {
        OversizeBundleRegistrar.register { stringifyBundle ->
            Log.e(
                "AppBundleWorker",
                "OVERSIZE BUNDLE DETECTED: $stringifyBundle"
            )
        }
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()

}