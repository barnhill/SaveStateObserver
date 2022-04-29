package com.pnuema.android.savestateobserver

import android.app.Application
import android.content.Context
import androidx.startup.Initializer
import com.pnuema.android.savestateobserver.observers.ActivitySaveStateObserver

@Suppress("unused") //initializer
class SaveStateLibraryInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        (context as? Application)?.registerActivityLifecycleCallbacks(ActivitySaveStateObserver())
    }
    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}
