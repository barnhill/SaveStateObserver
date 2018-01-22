package com.pnuema.savestateobserver.observers;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.pnuema.savestateobserver.BundlePrinter;

public final class ActivitySaveStateObserver extends BaseActivityLifecycleCallbacks {
    private final FragmentSaveStateObserver fragmentSaveStateObserver = new FragmentSaveStateObserver();
    private final FragmentSaveStateSupportObserver fragmentSaveStateSupportObserver = new FragmentSaveStateSupportObserver();

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        super.onActivityCreated(activity, bundle);

        if (activity instanceof FragmentActivity) {
            ((FragmentActivity)activity).getSupportFragmentManager().registerFragmentLifecycleCallbacks(fragmentSaveStateSupportObserver, true);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            activity.getFragmentManager().registerFragmentLifecycleCallbacks(fragmentSaveStateObserver, true);
        }
    }

    @Override
    public void onActivityStopped(Activity activity) {
        int index = activity.hashCode();
        BundlePrinter.printBundleContents(activity.getClass(), activity, getBundles().get(index));
        getBundles().remove(index);
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        getBundles().put(activity.hashCode(), bundle);
    }
}
