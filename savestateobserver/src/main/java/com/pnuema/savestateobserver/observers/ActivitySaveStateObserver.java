package com.pnuema.savestateobserver.observers;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.pnuema.savestateobserver.BundlePrinter;

public final class ActivitySaveStateObserver extends BaseActivityLifecycleCallbacks {
    @Override
    public void onActivityCreated(final Activity activity, final Bundle bundle) {
        super.onActivityCreated(activity, bundle);

        if (activity instanceof FragmentActivity) {
            final FragmentSaveStateSupportObserver fragmentSaveStateSupportObserver = new FragmentSaveStateSupportObserver();
            ((FragmentActivity)activity).getSupportFragmentManager().registerFragmentLifecycleCallbacks(fragmentSaveStateSupportObserver, true);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            final FragmentSaveStateObserver fragmentSaveStateObserver = new FragmentSaveStateObserver();
            activity.getFragmentManager().registerFragmentLifecycleCallbacks(fragmentSaveStateObserver, true);
        }
    }

    @Override
    public void onActivityStopped(final Activity activity) {
        final int index = activity.hashCode();
        BundlePrinter.printBundleContents(activity.getClass(), activity, getBundles().get(index));
        getBundles().remove(index);
    }

    @Override
    public void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {
        getBundles().put(activity.hashCode(), bundle);
    }
}
