package com.pnuema.android.savestateobserver.observers

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.pnuema.android.savestateobserver.BundlePrinter

class FragmentSaveStateSupportObserver : FragmentManager.FragmentLifecycleCallbacks() {
    override fun onFragmentSaveInstanceState(fragmentManager: FragmentManager, fragment: Fragment, outState: Bundle) {
        super.onFragmentSaveInstanceState(fragmentManager, fragment, outState)
        BundlePrinter.printBundleContents(fragment, outState)
    }
}
