package com.pnuema.android.savestateobserver.observers

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.pnuema.android.savestateobserver.BundlePrinter

class ActivitySaveStateObserver : BaseActivityLifecycleCallbacks() {
    override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
        super.onActivityCreated(activity, bundle)

        (activity as? FragmentActivity)?.supportFragmentManager?.registerFragmentLifecycleCallbacks(FragmentSaveStateSupportObserver(), true)
    }

    override fun onActivityStopped(activity: Activity) {
        val index = activity.hashCode()
        BundlePrinter.printBundleContents(activity, bundles.get(index))
        bundles.remove(index)
    }

    override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle?) {
        bundle?.let {
            bundles.put(activity.hashCode(), it)
        }
    }
}
