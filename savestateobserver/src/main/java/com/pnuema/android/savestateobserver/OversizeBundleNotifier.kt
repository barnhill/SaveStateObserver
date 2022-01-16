package com.pnuema.android.savestateobserver

import android.content.Context
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import java.util.*

object OversizeBundleNotifier {
    val BUNDLE_STRING_ID = "BUNDLE_STRING_ID"
    private val mapOversizeWorkRequests: HashMap<UUID, OneTimeWorkRequest.Builder> = hashMapOf()

    fun register(workRequest: OneTimeWorkRequest.Builder): UUID = UUID.randomUUID().apply {
        mapOversizeWorkRequests[this] = workRequest
    }

    fun unregister(id: UUID) {
        mapOversizeWorkRequests.remove(id)
    }

    fun notifyOversizeBundle(context: Context, stringifyBundle: String) {
        mapOversizeWorkRequests.values.forEach { workRequest ->
            WorkManager.getInstance(context).enqueue(
                workRequest.setInputData(
                    Data.Builder().putString(BUNDLE_STRING_ID, stringifyBundle).build()
                ).build()
            )
        }
    }
}