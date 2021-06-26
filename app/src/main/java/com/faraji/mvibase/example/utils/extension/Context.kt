package com.faraji.mvibase.example.utils.extension

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import coil.Coil
import coil.request.ImageRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun Context.getBitmapFromUrl(url: String): Bitmap? {
    val request = ImageRequest.Builder(this)
        .data(url)
        .allowHardware(false)
        .build()

    return withContext(Dispatchers.IO) {
        Coil.execute(request).drawable?.cast<BitmapDrawable>()?.bitmap
    }
}