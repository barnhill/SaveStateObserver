package com.pnuema.android.savestateobserver

import android.app.Application

import com.pnuema.android.savestateobserver.observers.ActivitySaveStateObserver

class SaveStateLibraryContentProvider : BaseContentProvider() {
    override fun onCreate(): Boolean {
        if (context is Application) {
            (context as Application).registerActivityLifecycleCallbacks(ActivitySaveStateObserver())
        }
        return super.onCreate()
    }
}
