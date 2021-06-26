package com.faraji.mvibase.example.utils.extension

import androidx.recyclerview.widget.RecyclerView
import com.faraji.mvibase.example.utils.RecyclerViewEndListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.isActive

fun RecyclerView.loadMores() = channelFlow {
    val listener = listener(this, ::trySend)
    addOnScrollListener(listener)
    awaitClose { removeOnScrollListener(listener) }
}

private fun listener(
    scope: CoroutineScope,
    emitter: (Unit) -> Unit
) = object : RecyclerViewEndListener() {
    override fun onLoadMore() {
        if (scope.isActive) {
            emitter(Unit)
        }
    }
}