package com.pnuema.android.savestateobserver

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * Base class for the bundle worker to be notified.  Implemented by the consumer to handle
 * work when an oversize bundle is detected.  Must be registered in the [OversizeBundleRegistrar.register]
 */
abstract class OversizeBundleWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams), IBundleNotifyWorker {
    final override fun doWork(): Result {
        doWork(OversizeBundleRegistrar.getBundle(inputData))
        return Result.success()
    }
}