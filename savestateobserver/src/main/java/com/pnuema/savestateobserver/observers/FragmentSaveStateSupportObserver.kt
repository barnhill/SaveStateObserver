package com.pnuema.savestateobserver.observers

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.util.SparseArray
import com.pnuema.savestateobserver.BundlePrinter

class FragmentSaveStateSupportObserver : FragmentManager.FragmentLifecycleCallbacks() {
    private val bundles = SparseArray<Bundle>()

    override fun onFragmentStopped(fragmentManager: FragmentManager?, fragment: Fragment?) {
        super.onFragmentStopped(fragmentManager, fragment)

        val context = fragment!!.context ?: return

        bundles.remove(fragment.hashCode())
        BundlePrinter.printBundleContents(fragment.javaClass, context, bundles.get(fragment.hashCode()))
    }

    override fun onFragmentSaveInstanceState(fragmentManager: FragmentManager?, fragment: Fragment?, outState: Bundle?) {
        super.onFragmentSaveInstanceState(fragmentManager, fragment, outState)

        bundles.put(fragment!!.hashCode(), outState)
    }
}
