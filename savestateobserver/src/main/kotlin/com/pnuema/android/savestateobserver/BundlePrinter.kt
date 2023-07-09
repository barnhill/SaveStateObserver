package com.pnuema.android.savestateobserver

import android.content.Context
import android.os.Bundle
import android.os.Parcel
import android.util.Log
import androidx.fragment.app.Fragment
import java.nio.CharBuffer
import java.util.*

/**
 * Prints the contents of bundles
 */
internal object BundlePrinter {
    private const val SPACE_OFFSET = 4
    private const val BUNDLE_SIZE_THRESHOLD = 50000 //Recommended by Google (https://developer.android.com/guide/components/activities/parcelables-and-bundles.html#sdbp)

    fun printBundleContents(context: Context, bundle: Bundle?) {
        printBundleContentsInternal(context = context, clazz = context.javaClass, bundle = bundle)
    }

    fun printBundleContents(fragment: Fragment, bundle: Bundle?) {
        printBundleContentsInternal(context = fragment.requireContext(), clazz = fragment.javaClass, bundle = bundle)
    }

    private fun printBundleContentsInternal(context: Context, clazz: Class<*>, bundle: Bundle?) {
        //TODO multiple logging levels
        if (bundle == null) {
            Log.d(context.getString(R.string.libname), context.getString(R.string.banner) + System.lineSeparator() + context.getString(R.string.bundle_empty))
            return
        }

        val bundleSizeRaw = getBundleTotalSize(bundle)
        if (bundleSizeRaw > BUNDLE_SIZE_THRESHOLD) {
            Log.w(context.getString(R.string.libname), context.getString(R.string.bundle_overthreshold, condense(context, bundleSizeRaw)))
            OversizeBundleRegistrar.notifyOversizeBundle(stringifyBundle = stringifyBundle(context = context, clazz = clazz, bundle = bundle))
        }

        Log.d(context.getString(R.string.libname), context.getString(R.string.banner) + System.lineSeparator() + stringifyBundle(context = context, clazz = clazz, bundle = bundle))
    }

    private fun stringifyBundle(context: Context, clazz: Class<*>, bundle: Bundle) = getHeaderAsString(context, clazz, bundle) + System.lineSeparator() + getContents(context, 1, bundle)

    private fun getHeaderAsString(context: Context, clazz: Class<*>, bundle: Bundle): String {
        return context.resources.getQuantityString(R.plurals.title, bundle.size(), clazz.simpleName, System.identityHashCode(bundle), bundle.size(), condense(context, getBundleTotalSize(bundle)))
    }

    private fun getBundleTotalSize(bundle: Bundle): Int {
        val parcel = Parcel.obtain()
        try {
            parcel.writeBundle(bundle)
            return parcel.dataSize()
        } finally {
            parcel.recycle()
        }
    }

    private fun getContents(context: Context, layer: Int, bundle: Bundle): String {
        val contents = StringBuilder()
        bundle.keySet().forEach { key ->
            if (bundle.getBundle(key) is Bundle) {
                val innerBundle = bundle.getBundle(key) as Bundle
                contents.apply {
                    append(context.resources.getQuantityString(R.plurals.bundle_entry_format, innerBundle.size(), getPrefix(layer), System.identityHashCode(innerBundle), key, innerBundle.size(), condense(context, getBundleTotalSize(innerBundle))))
                    append(System.lineSeparator())
                    append(getContents(context, layer + 1, innerBundle))
                }
            } else {
                contents.apply {
                    append(context.getString(R.string.entry_format, getPrefix(layer), key, sizeOf(context, bundle, key)))
                    append(System.lineSeparator())
                }
            }
        }

        System.out.println(contents.toString())

        return contents.toString()
    }

    private fun sizeOf(context: Context, bundle: Bundle, key: String): String {
        Parcel.obtain().apply {
            writeValue(bundle.getBundle(key))
            val returnValue = dataSize()
            recycle()

            return condense(context, returnValue)
        }
    }

    private fun condense(context: Context, bytes: Int): String {
        return if (bytes > 1000) {
            context.getString(R.string.kilobyte_suffix, String.format(Locale.getDefault(), "%.2f", bytes / 1000f))
        } else {
            context.getString(R.string.bytes_suffix, bytes.toString())
        }
    }

    private fun getPrefix(layer: Int): String = CharBuffer.allocate(layer * SPACE_OFFSET)
        .toString()
        .replace('\u0000', ' ')
}
