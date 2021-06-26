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

abstract class MviFragment<VB : ViewBinding, I : MviIntent, S : MviViewState, E : MviViewEvent, VM : MviViewModel<I, *, S, E>> :
    BaseMviFragment<VB>() {

    companion object {
        const val TAG = "IntentFragment"
    }

    private val intentChanel by lazy { Channel<I>(Channel.BUFFERED) }

    abstract val viewModel: VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.state
            .onEach { handleState(it) }
            .onEach { Log.d(TAG, "State Received: $it") }
            .catch { Log.e(TAG, "Error", it) }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        merge(intents(), intentChanel.receiveAsFlow())
            .onEach { viewModel.intentChannel.send(it) }
            .onEach { Log.d(TAG, "Intent Sent: $it") }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.events
            .onEach { logD(TAG, "Event Received: $it") }
            .onEach { handleEvent(it) }
            .catch { logE(TAG, "Error", it) }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        super.onViewCreated(view, savedInstanceState)
    }

    abstract fun intents(): Flow<I>

    abstract suspend fun handleState(state: S)

    abstract suspend fun handleEvent(event: E)

    fun sendIntent(intent: I) {
        intentChanel.trySend(intent)
    }

    override fun onDestroy() {
        intentChanel.close()
        super.onDestroy()
    }
}