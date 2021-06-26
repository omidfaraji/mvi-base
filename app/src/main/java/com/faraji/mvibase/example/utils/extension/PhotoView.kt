package com.faraji.mvibase.example.utils.extension

import com.github.chrisbanes.photoview.PhotoView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun PhotoView.load(
    url: String,
    coroutineScope: CoroutineScope,
    onFail: () -> Unit = {}
) {
    url.takeIf { it.isNotBlank() } ?: return
    coroutineScope.launch {
        val bitmap = context.getBitmapFromUrl(url) ?: run {
            onFail()
            return@launch
        }
        setImageBitmap(bitmap)
    }
}
