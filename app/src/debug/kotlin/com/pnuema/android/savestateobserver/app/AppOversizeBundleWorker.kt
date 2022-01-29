package com.pnuema.android.savestateobserver.app

import android.content.Context
import android.util.Log
import androidx.work.WorkerParameters
import com.pnuema.android.savestateobserver.OversizeBundleWorker

/**
 * Example definition of the worker in an app to do some work when an oversized bundle is detected.
 * This will be registered on app launch with OversizeBundleNotifier.
 */
class AppOversizeBundleWorker(
    context: Context,
    workerParams: WorkerParameters
) : OversizeBundleWorker(context, workerParams) {
    override fun doWork(stringifyBundle: String?) {
        Log.e(
            "AppBundleWorker",
            "OVERSIZE BUNDLE DETECTED: $stringifyBundle"
        )
    }
}