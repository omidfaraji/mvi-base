package com.faraji.mvibase.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.faraji.mvibase.presentation.MviIntent
import com.faraji.mvibase.presentation.MviViewEvent
import com.faraji.mvibase.presentation.MviViewModel
import com.faraji.mvibase.presentation.MviViewState
import com.faraji.mvibase.utils.logD
import com.faraji.mvibase.utils.logE
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

abstract class MviBottomSheet<VB : ViewBinding, I : MviIntent, S : MviViewState, E : MviViewEvent, VM : MviViewModel<I, *, S, E>> :
    BaseMviBottomSheet<VB>() {

    companion object {
        const val TAG = "IntentBottomSheet"
    }

    private val intentChanel by lazy { Channel<I>(Channel.BUFFERED) }

    abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        merge(intents(), intentChanel.receiveAsFlow())
            .onEach { viewModel.intentChannel.send(it) }
            .onEach { Log.d(TAG, "Intent Sent: $it") }
            .launchIn(lifecycleScope)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.state
            .onEach { logD(TAG, "State Received: $it") }
            .onEach { handleState(it) }
            .catch { logE(TAG, "Error", it) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
        viewModel.events
            .onEach { logD(TAG, "Event Received: $it") }
            .onEach { handleEvent(it) }
            .catch { logE(TAG, "Error", it) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
        super.onViewCreated(view, savedInstanceState)
    }

    abstract suspend fun handleState(state: S)

    abstract suspend fun handleEvent(event: E)


    abstract fun intents(): Flow<I>

    fun sendIntent(intent: I) {
        intentChanel.trySend(intent)
    }

    override fun onDestroy() {
        intentChanel.close()
        super.onDestroy()
    }
}
