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
    public void onFragmentStopped(FragmentManager fm, Fragment f) {
        super.onFragmentStopped(fm, f);

        Context context = f.getContext();

        if (context == null) {
            return;
        }

        Integer index = f.hashCode();
        bundles.remove(index);
        BundlePrinter.printBundleContents(f.getClass(), context, bundles.get(index));
    }

    @Override
    public void onFragmentSaveInstanceState(FragmentManager fm, Fragment f, Bundle outState) {
        super.onFragmentSaveInstanceState(fm, f, outState);

        bundles.put(f.hashCode(), outState);
    }
}
