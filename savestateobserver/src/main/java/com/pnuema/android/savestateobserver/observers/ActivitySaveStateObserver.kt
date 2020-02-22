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

    override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle?) {
        BundlePrinter.printBundleContents(activity, bundle)
    }
}
