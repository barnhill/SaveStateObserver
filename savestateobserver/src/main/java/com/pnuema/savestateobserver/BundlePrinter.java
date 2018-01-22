package com.pnuema.savestateobserver;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.util.Log;

import java.nio.CharBuffer;
import java.util.Locale;

public class BundlePrinter {
    private static final String TAG = BundlePrinter.class.getSimpleName();
    private static final int SPACE_OFFSET = 4;

    @SuppressWarnings("ConstantConditions")
    public static void printBundleContents(@NonNull Class clazz, @NonNull Context context, @NonNull Bundle bundle) {
        if (context == null || bundle == null) {
            return;
        }
        //TODO multiple logging levels
        Log.d(TAG, context.getString(R.string.banner) + "\n" + getHeaderAsString(clazz, context, bundle) + "\n" + getContents(context, 1, bundle));
    }

    private static String getHeaderAsString(@NonNull Class clazz, @NonNull Context context, @NonNull Bundle bundle) {
        return context.getString(R.string.title, clazz.getSimpleName(), System.identityHashCode(bundle), bundle.size(), condense(context, getBundleTotalSize(bundle)));
    }

    @SuppressWarnings("ConstantConditions")
    private static int getBundleTotalSize(@NonNull Bundle bundle) {
        if (bundle == null) {
            return 0;
        }

        Parcel parcel = Parcel.obtain();
        try {
            parcel.writeBundle(bundle);
            return parcel.dataSize();
        } finally {
            parcel.recycle();
        }
    }

    private static String getContents(Context context, int layer, Bundle bundle) {
        StringBuilder contents = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (bundle.get(key) instanceof Bundle) {
                Bundle innerBundle = (Bundle) bundle.get(key);
                contents.append(context.getString(R.string.bundle_entry_format, getPrefix(layer), System.identityHashCode(innerBundle), key, innerBundle.size(), condense(context, getBundleTotalSize(innerBundle))));
                contents.append('\n');
                contents.append(getContents(context, layer + 1, (Bundle) bundle.get(key)));
            } else {
                contents.append(context.getString(R.string.entry_format, getPrefix(layer), key, sizeOf(context, bundle, key)));
                contents.append('\n');
            }
        }

        return contents.toString();
    }

    private static String sizeOf(Context context, Bundle bundle, String key) {
        Parcel parcel = Parcel.obtain();
        parcel.writeValue(bundle.get(key));
        int returnValue = parcel.dataSize();
        parcel.recycle();

        return condense(context, returnValue);
    }

    private static String condense(Context context, int bytes) {
        if (bytes > 1000) {
            return context.getString(R.string.kilobyte_suffix, String.format(Locale.getDefault(), "%.2f", bytes / 1000f));
        } else {
            return context.getString(R.string.bytes_suffix, String.valueOf(bytes));
        }
    }

    private static String getPrefix(int layer) {
        return CharBuffer.allocate(layer * SPACE_OFFSET).toString().replace('\0', ' ');
    }
}
