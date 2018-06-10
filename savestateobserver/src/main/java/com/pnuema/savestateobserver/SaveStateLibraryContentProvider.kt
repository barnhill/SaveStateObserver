package com.pnuema.savestateobserver

import android.app.Application

import com.pnuema.savestateobserver.observers.ActivitySaveStateObserver

class SaveStateLibraryContentProvider : BaseContentProvider() {
    override fun onCreate(): Boolean {
        if (context is Application) {
            (context as Application).registerActivityLifecycleCallbacks(ActivitySaveStateObserver())
        }
        return super.onCreate()
    }
}
