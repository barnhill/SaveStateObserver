package com.pnuema.android.savestateobserver.observers

import android.os.Bundle
import android.util.SparseArray
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.pnuema.android.savestateobserver.BundlePrinter

class FragmentSaveStateSupportObserver : FragmentManager.FragmentLifecycleCallbacks() {
    private val bundles = SparseArray<Bundle>()

    override fun onFragmentStopped(fragmentManager: FragmentManager, fragment: Fragment) {
        super.onFragmentStopped(fragmentManager, fragment)

        bundles.remove(fragment.hashCode())
    }

    override fun onFragmentSaveInstanceState(fragmentManager: FragmentManager, fragment: Fragment, outState: Bundle) {
        super.onFragmentSaveInstanceState(fragmentManager, fragment, outState)

        val hashcode = fragment.hashCode()
        bundles.put(hashcode, outState)
        BundlePrinter.printBundleContents(fragment.context ?: return, bundles.get(hashcode))
    }
}
