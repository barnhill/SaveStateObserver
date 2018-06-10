package com.pnuema.savestateobserver

import android.content.Context
import android.os.Bundle
import android.os.Parcel
import android.util.Log
import java.nio.CharBuffer
import java.util.*

object BundlePrinter {
    private val TAG = BundlePrinter::class.java.simpleName
    private const val SPACE_OFFSET = 4

    fun printBundleContents(clazz: Class<*>, context: Context, bundle: Bundle) {
        //TODO multiple logging levels
        Log.d(TAG, context.getString(R.string.banner) + System.lineSeparator() + getHeaderAsString(clazz, context, bundle) + System.lineSeparator() + getContents(context, 1, bundle))
    }

    private fun getHeaderAsString(clazz: Class<*>, context: Context, bundle: Bundle): String {
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
        for (key in bundle.keySet()) {
            if (bundle.get(key) is Bundle) {
                val innerBundle = bundle.get(key) as Bundle
                contents.append(context.resources.getQuantityString(R.plurals.bundle_entry_format, innerBundle.size(), getPrefix(layer), System.identityHashCode(innerBundle), key, innerBundle.size(), condense(context, getBundleTotalSize(innerBundle))))
                contents.append('\n')
                contents.append(getContents(context, layer + 1, Objects.requireNonNull(bundle.get(key)) as Bundle))
            } else {
                contents.append(context.getString(R.string.entry_format, getPrefix(layer), key, sizeOf(context, bundle, key)))
                contents.append('\n')
            }
        }

        return contents.toString()
    }

    private fun sizeOf(context: Context, bundle: Bundle, key: String): String {
        val parcel = Parcel.obtain()
        parcel.writeValue(bundle.get(key))
        val returnValue = parcel.dataSize()
        parcel.recycle()

        return condense(context, returnValue)
    }

    private fun condense(context: Context, bytes: Int): String {
        return if (bytes > 1000) {
            context.getString(R.string.kilobyte_suffix, String.format(Locale.getDefault(), "%.2f", bytes / 1000f))
        } else {
            context.getString(R.string.bytes_suffix, bytes.toString())
        }
    }

    private fun getPrefix(layer: Int): String {
        return CharBuffer.allocate(layer * SPACE_OFFSET).toString().replace('\u0000', ' ')
    }
}
