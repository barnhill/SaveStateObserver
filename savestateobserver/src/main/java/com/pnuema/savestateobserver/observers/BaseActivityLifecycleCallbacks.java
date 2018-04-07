package com.pnuema.savestateobserver.observers;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.SparseArray;

public abstract class BaseActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
    private final SparseArray<Bundle> bundles = new SparseArray<>();

    @Override
    public void onActivityCreated(final Activity activity, final Bundle bundle) {
    }

    @Override
    public void onActivityStarted(final Activity activity) {
    }

    @Override
    public void onActivityResumed(final Activity activity) {
    }

    @Override
    public void onActivityPaused(final Activity activity) {
    }

    @Override
    public void onActivityStopped(final Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {
    }

    @Override
    public void onActivityDestroyed(final Activity activity) {
    }

    SparseArray<Bundle> getBundles() {
        return bundles;
    }
}
