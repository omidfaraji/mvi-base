package com.faraji.mvibase.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faraji.mvibase.utils.logD
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


abstract class MviViewModel<I : MviIntent, A : MviAction, S : MviViewState, E : MviViewEvent> :
    ViewModel() {
    companion object {
        const val TAG = "IntentViewModel"
    }

    val intentChannel = Channel<I>(Channel.BUFFERED)

    private val internalState by lazy { MutableStateFlow(getInitialState()) }

    val state: Flow<S>
        get() = internalState.onEach { saveViewState(it) }


    val stateValue: S
        get() = internalState.value

    private val eventChannel = Channel<E>(Channel.BUFFERED)

    val events: Flow<E>
        get() = eventChannel.receiveAsFlow()


    fun sendEvent(eventState: E) {
        eventChannel.trySend(eventState)
        logD(TAG, "Event Sent: $eventState")
    }


    init {
        viewModelScope.launch {
            processIntents()
        }
    }

    abstract fun getInitialState(): S


    open fun saveViewState(viewState: S) {

    }

    private fun processIntents() {
        intentChannel.consumeAsFlow()
            .onEach { logD(TAG, "Intent Received: $it") }
            .map { intentToAction(it) }
            .onEach { handleActions(it) }
            .launchIn(viewModelScope)
    }


    abstract fun intentToAction(intent: I): A

    abstract suspend fun handleActions(action: A)

    open fun sendState(state: S) {
        internalState.value = state
        logD(TAG, "State Sent: $state")
    }


    fun sendStateAndClear(state: S) {
        sendState(state)
    }


    fun clearState() {
        internalState.value = getInitialState()
        logD(TAG, "State Sent: $state")
    }

    override fun onCleared() {
        intentChannel.close()
        eventChannel.close()
    }

}