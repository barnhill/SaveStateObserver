package com.pnuema.savestateobserver.observers;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.SparseArray;

public abstract class BaseActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
    private final SparseArray<Bundle> bundles = new SparseArray<>();

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override
    public void onActivityStarted(Activity activity) {
    }

    @Override
    public void onActivityResumed(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivityStopped(Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
    }

    SparseArray<Bundle> getBundles() {
        return bundles;
    }
}
