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
