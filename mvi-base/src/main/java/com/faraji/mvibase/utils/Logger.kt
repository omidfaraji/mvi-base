package com.faraji.mvibase.utils

import android.util.Log
import com.faraji.mvibase.MviBaseConfig

fun logD(tag: String, msg: String) {
    if (MviBaseConfig.debugLog)
        Log.d(tag, msg)
}

fun logE(tag: String, msg: String, throwable: Throwable) {
    if (MviBaseConfig.debugLog)
        Log.e(tag, msg, throwable)
}