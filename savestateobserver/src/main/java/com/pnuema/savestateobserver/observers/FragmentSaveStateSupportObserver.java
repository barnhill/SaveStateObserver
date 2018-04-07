package com.pnuema.savestateobserver.observers;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.SparseArray;

import com.pnuema.savestateobserver.BundlePrinter;

public final class FragmentSaveStateSupportObserver extends FragmentManager.FragmentLifecycleCallbacks {
    private final SparseArray<Bundle> bundles = new SparseArray<>();

    @Override
    public void onFragmentStopped(final FragmentManager fragmentManager, final Fragment fragment) {
        super.onFragmentStopped(fragmentManager, fragment);

        final Context context = fragment.getContext();

        if (context == null) {
            return;
        }

        final int index = fragment.hashCode();
        bundles.remove(index);
        BundlePrinter.printBundleContents(fragment.getClass(), context, bundles.get(index));
    }

    @Override
    public void onFragmentSaveInstanceState(final FragmentManager fragmentManager, final Fragment fragment, final Bundle outState) {
        super.onFragmentSaveInstanceState(fragmentManager, fragment, outState);

        bundles.put(fragment.hashCode(), outState);
    }
}
