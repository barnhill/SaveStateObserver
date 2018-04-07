package com.pnuema.savestateobserver;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public abstract class BaseContentProvider extends ContentProvider {
    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull final Uri uri, @Nullable final String[] projection, @Nullable final String selection, @Nullable final String[] selectionArgs, @Nullable final String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull final Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull final Uri uri, @Nullable final ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull final Uri uri, @Nullable final String selection, @Nullable final String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull final Uri uri, @Nullable final ContentValues values, @Nullable final String selection, @Nullable final String[] selectionArgs) {
        return 0;
    }
}
