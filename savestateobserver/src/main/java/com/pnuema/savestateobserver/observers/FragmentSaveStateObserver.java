package com.pnuema.savestateobserver.observers;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.SparseArray;

import com.pnuema.savestateobserver.BundlePrinter;

@RequiresApi(api = Build.VERSION_CODES.O)
public final class FragmentSaveStateObserver extends FragmentManager.FragmentLifecycleCallbacks {
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
