package com.pnuema.android.savestateobserver

internal interface IBundleNotifyWorker {
    /**
     * Method to be overridden in the worker to receive the string representation of the bundle
     */
    fun doWork(stringifyBundle: String?)
}
