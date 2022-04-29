package com.pnuema.android.savestateobserver.app

import android.content.Context
import androidx.startup.Initializer

class AppInitializer: Initializer<Unit> {
    override fun create(context: Context) = Unit
    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}