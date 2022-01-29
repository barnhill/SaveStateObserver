package com.pnuema.android.savestateobserver.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import com.pnuema.android.savestateobserver.OversizeBundleRegistrar
import java.util.*

open class BaseActivity: AppCompatActivity() {

    private lateinit var uuid: UUID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        uuid = OversizeBundleRegistrar.register(
            OneTimeWorkRequest.Builder(AppOversizeBundleWorker::class.java)
                .setConstraints(
                    Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build()
                )
        )
    }

    override fun onDestroy() {
        if (::uuid.isInitialized) {
            OversizeBundleRegistrar.unregister(uuid)
        }
        super.onDestroy()
    }
}