package com.pnuema.android.savestateobserver

import java.util.*
import javax.crypto.Cipher

/**
 * Handles registration of workers to be notified should an oversize bundle be detected.
 */
@Suppress("unused")
object OversizeBundleRegistrar {
    private val mapOversizeWorkRequests: HashMap<UUID, (stringifyBundle: String) -> Unit> = hashMapOf()

    /**
     * Register for notification when an oversize bundle has been detected.
     * @param listener Listener that will be notified when an oversize bundle is detected
     * @return UUID that is an identifier for the listener that was registered.  This is used to unregister the listener.
     */
    fun register(listener: (stringifyBundle: String) -> Unit): UUID = UUID.randomUUID().apply {
        mapOversizeWorkRequests[this] = listener
    }

    /**
     * Unregister the listener with the corresponding id.
     * @param id UUID identifying the listener to remove.
     */
    fun unregister(id: UUID) {
        mapOversizeWorkRequests.remove(id)
    }

    internal fun notifyOversizeBundle(stringifyBundle: String) {
        mapOversizeWorkRequests.values.forEach { listener ->
            listener.invoke(stringifyBundle)
        }
    }
}