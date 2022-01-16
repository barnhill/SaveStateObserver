package com.pnuema.android.savestateobserver.app

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.pnuema.android.savestateobserver.OversizeBundleNotifier

class OversizedBundleWorker(
    context: Context, workerParams: WorkerParameters
) : Worker(context, workerParams) {
    override fun doWork(): Result {
        Log.e(
            "OversizedBundleWorker",
            "OVERSIZE BUNDLE DETECTED: ${inputData.getString(OversizeBundleNotifier.BUNDLE_STRING_ID)}"
        )
        return Result.success()
    }
}