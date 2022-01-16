package com.pnuema.android.savestateobserver

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class OversizeWorkUnit(
    appContext: Context,
    workerParams: WorkerParameters,
    private val workUnit: () -> Unit
) : Worker(appContext, workerParams) {
    override fun doWork(): Result {
        workUnit.invoke()
        return Result.success()
    }
}