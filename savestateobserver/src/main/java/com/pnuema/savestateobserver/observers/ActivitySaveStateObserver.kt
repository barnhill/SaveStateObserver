package com.pnuema.savestateobserver.observers

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.support.v4.app.FragmentActivity

import com.pnuema.savestateobserver.BundlePrinter

class ActivitySaveStateObserver : BaseActivityLifecycleCallbacks() {
    override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
        super.onActivityCreated(activity, bundle)

        if (activity is FragmentActivity) {
            val fragmentSaveStateSupportObserver = FragmentSaveStateSupportObserver()
            activity.supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentSaveStateSupportObserver, true)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val fragmentSaveStateObserver = FragmentSaveStateObserver()
            activity.fragmentManager.registerFragmentLifecycleCallbacks(fragmentSaveStateObserver, true)
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
