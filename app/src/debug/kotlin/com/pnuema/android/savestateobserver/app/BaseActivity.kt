package com.pnuema.android.savestateobserver.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import com.pnuema.android.savestateobserver.OversizeBundleNotifier
import java.util.*

open class BaseActivity: AppCompatActivity() {

    private lateinit var uuid: UUID

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