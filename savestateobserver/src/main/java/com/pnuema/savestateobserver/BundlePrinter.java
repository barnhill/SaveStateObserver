package com.pnuema.savestateobserver;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.util.Log;

import java.nio.CharBuffer;
import java.util.Locale;
import java.util.Objects;

public final class BundlePrinter {
    private static final String TAG = BundlePrinter.class.getSimpleName();
    private static final int SPACE_OFFSET = 4;

    private BundlePrinter() {
    }

    @SuppressWarnings("ConstantConditions")
    public static void printBundleContents(@NonNull final Class clazz, @NonNull final Context context, @NonNull final Bundle bundle) {
        if (context == null || bundle == null) {
            return;
        }
        //TODO multiple logging levels
        Log.d(TAG, context.getString(R.string.banner) + System.lineSeparator() + getHeaderAsString(clazz, context, bundle) + System.lineSeparator() + getContents(context, 1, bundle));
    }

    private static String getHeaderAsString(@NonNull final Class clazz, @NonNull final Context context, @NonNull final Bundle bundle) {
        return context.getResources().getQuantityString(R.plurals.title, bundle.size(), clazz.getSimpleName(), System.identityHashCode(bundle), bundle.size(), condense(context, getBundleTotalSize(bundle)));
    }

    @SuppressWarnings("ConstantConditions")
    private static int getBundleTotalSize(@NonNull final Bundle bundle) {
        if (bundle == null) {
            return 0;
        }

        final Parcel parcel = Parcel.obtain();
        try {
            parcel.writeBundle(bundle);
            return parcel.dataSize();
        } finally {
            parcel.recycle();
        }
    }

    private static String getContents(final Context context, final int layer, final Bundle bundle) {
        final StringBuilder contents = new StringBuilder();
        for (final String key : bundle.keySet()) {
            if (bundle.get(key) instanceof Bundle) {
                final Bundle innerBundle = (Bundle) bundle.get(key);
                if (innerBundle != null) {
                    contents.append(context.getResources().getQuantityString(R.plurals.bundle_entry_format, innerBundle.size(), getPrefix(layer), System.identityHashCode(innerBundle), key, innerBundle.size(), condense(context, getBundleTotalSize(innerBundle))));
                    contents.append('\n');
                    contents.append(getContents(context, layer + 1, (Bundle) Objects.requireNonNull(bundle.get(key))));
                }
            } else {
                contents.append(context.getString(R.string.entry_format, getPrefix(layer), key, sizeOf(context, bundle, key)));
                contents.append('\n');
            }
        }

        return contents.toString();
    }

    private static String sizeOf(final Context context, final Bundle bundle, final String key) {
        final Parcel parcel = Parcel.obtain();
        parcel.writeValue(bundle.get(key));
        final int returnValue = parcel.dataSize();
        parcel.recycle();

        return condense(context, returnValue);
    }

    private static String condense(final Context context, final int bytes) {
        if (bytes > 1000) {
            return context.getString(R.string.kilobyte_suffix, String.format(Locale.getDefault(), "%.2f", bytes / 1000f));
        } else {
            return context.getString(R.string.bytes_suffix, String.valueOf(bytes));
        }
    }

    private static String getPrefix(final int layer) {
        return CharBuffer.allocate(layer * SPACE_OFFSET).toString().replace('\0', ' ');
    }
}
