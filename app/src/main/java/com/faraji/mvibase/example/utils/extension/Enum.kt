package com.faraji.mvibase.example.utils.extension

import com.google.gson.annotations.SerializedName
import timber.log.Timber

fun <E : Enum<*>> E.getSerializedNameValue(): String? {
    return try {
        javaClass.getField(name).getAnnotation(SerializedName::class.java).value
    } catch (e: NoSuchFieldException) {
        Timber.e(e)
        null
    }
}