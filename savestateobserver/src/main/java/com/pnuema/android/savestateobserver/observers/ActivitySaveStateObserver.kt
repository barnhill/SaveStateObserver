package com.pnuema.android.savestateobserver.observers

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.pnuema.android.savestateobserver.BundlePrinter

class ActivitySaveStateObserver : BaseActivityLifecycleCallbacks() {
    override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
        super.onActivityCreated(activity, bundle)

        if (activity is FragmentActivity) {
            val fragmentSaveStateSupportObserver = FragmentSaveStateSupportObserver()
            activity.supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentSaveStateSupportObserver, true)
        }
    }

    override fun onActivityStopped(activity: Activity) {
        val index = activity.hashCode()
        BundlePrinter.printBundleContents(activity.javaClass, activity, bundles.get(index))
        bundles.remove(index)
    }

    override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle?) {
        if (bundle != null) {
            bundles.put(activity.hashCode(), bundle)
        }
    }
}
