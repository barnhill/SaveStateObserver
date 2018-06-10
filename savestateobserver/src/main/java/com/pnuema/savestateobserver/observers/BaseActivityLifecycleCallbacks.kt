package com.pnuema.savestateobserver.observers

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.SparseArray

abstract class BaseActivityLifecycleCallbacks : Application.ActivityLifecycleCallbacks {
    internal val bundles = SparseArray<Bundle>()

    override fun onActivityCreated(activity: Activity, bundle: Bundle?) {}

    override fun onActivityStarted(activity: Activity) {}

    override fun onActivityResumed(activity: Activity) {}

    override fun onActivityPaused(activity: Activity) {}

    override fun onActivityStopped(activity: Activity) {}

    override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle?) {}

    override fun onActivityDestroyed(activity: Activity) {}
}
