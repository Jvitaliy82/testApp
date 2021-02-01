package com.appcraft.testapp.app.db

import android.content.Context
import com.appcraft.testapp.BuildConfig

import io.objectbox.BoxStore
import io.objectbox.android.AndroidObjectBrowser
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

class ObjectBoxBrowser {
    fun init(boxStore: BoxStore, context: Context) {
        if (BuildConfig.DEBUG) {
            val result = AndroidObjectBrowser(boxStore).start(context)
            logger.info("androidObjectBox init = $result")
        }
    }
}
