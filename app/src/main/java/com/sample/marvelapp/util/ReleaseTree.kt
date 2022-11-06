package com.sample.marvelapp.util

import android.util.Log
import timber.log.Timber

class ReleaseTree: Timber.Tree() {
    override fun isLoggable(tag: String?, priority: Int): Boolean {
        return priority >= Log.WARN
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        // do nothing
    }
}