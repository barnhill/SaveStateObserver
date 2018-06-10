package com.pnuema.savestateobserver.observers

import android.app.Fragment
import android.app.FragmentManager
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.util.SparseArray
import com.pnuema.savestateobserver.BundlePrinter

@RequiresApi(api = Build.VERSION_CODES.O)
class FragmentSaveStateObserver : FragmentManager.FragmentLifecycleCallbacks() {
    private val bundles = SparseArray<Bundle>()

    override fun onFragmentStopped(fragmentManager: FragmentManager, fragment: Fragment) {
        super.onFragmentStopped(fragmentManager, fragment)

        val context = fragment.context ?: return

        val index = fragment.hashCode()
        bundles.remove(index)
        BundlePrinter.printBundleContents(fragment.javaClass, context, bundles.get(index))
    }

    override fun onFragmentSaveInstanceState(fragmentManager: FragmentManager, fragment: Fragment, outState: Bundle) {
        super.onFragmentSaveInstanceState(fragmentManager, fragment, outState)

        bundles.put(fragment.hashCode(), outState)
    }
}
