package com.faraji.mvibase.view

import android.os.Bundle
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

abstract class MviActivity<VB : ViewBinding, I : MviIntent, S : MviViewState, E : MviViewEvent, VM : MviViewModel<I, *, S, E>> :
    BaseMviActivity<VB>() {


    companion object {
        const val TAG = "IntentActivity"
    }

    abstract val viewModel: VM

    private val intentChanel by lazy { Channel<I>(Channel.BUFFERED) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenStarted {
            viewModel.state
                .onEach { logD(TAG, "State Received: $it") }
                .onEach { handleState(it) }
                .catch { logE(TAG, "Error", it) }
                .collect()
        }

        merge(intents(), intentChanel.receiveAsFlow())
            .onEach { viewModel.intentChannel.send(it) }
            .launchIn(lifecycleScope)

        viewModel.events
            .onEach { logD(TAG, "Event Received: $it") }
            .onEach { handleEvent(it) }
            .catch { logE(TAG, "Error", it) }
            .launchIn(lifecycleScope)
    }

    abstract suspend fun handleState(state: S)

    abstract suspend fun handleEvent(event: E)

    fun sendIntent(intent: I) {
        intentChanel.trySend(intent)
    }


    abstract fun intents(): Flow<I>

    override fun onDestroy() {
        intentChanel.close()
        super.onDestroy()
    }

}