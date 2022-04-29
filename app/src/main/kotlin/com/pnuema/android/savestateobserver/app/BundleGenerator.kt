package com.pnuema.android.savestateobserver.app

import android.os.Bundle
import java.util.*

object BundleGenerator {
    /**
     * Generate oversize bundle
     */
    fun Bundle.generateOversizeBundle() = apply {
        putInt("Integer", 1234)
        putString("String", "StringTest")
        putFloat("Float", 12.34F)

        val innerBundle = Bundle()
        innerBundle.putInt("Integer", 5678)
        innerBundle.putString("String", "InnerStringTest")
        innerBundle.putFloat("Float", 56.78F)

        //generate 50k of data for the bundle
        var bigString = ""
        while (bigString.length < 50000) {
            bigString += UUID.randomUUID().toString()
        }
        innerBundle.putString("BigString", bigString)

        putBundle("innerBundle", innerBundle)
    }
}