package com.pnuema.savestateobserver;

import android.app.Application;

import com.pnuema.savestateobserver.observers.ActivitySaveStateObserver;

public final class SaveStateLibraryContentProvider extends BaseContentProvider {
    @Override
    public boolean onCreate() {
        if (getContext() instanceof Application) {
            ((Application) getContext()).registerActivityLifecycleCallbacks(new ActivitySaveStateObserver());
        }
        return super.onCreate();
    }
}
