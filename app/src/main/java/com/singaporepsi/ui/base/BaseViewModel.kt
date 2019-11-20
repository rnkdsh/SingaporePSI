package com.singaporepsi.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.singaporepsi.result.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel() {

    private val parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default
    val scope = CoroutineScope(coroutineContext)

    val messageLiveData = MutableLiveData<Event<String?>>()
    val showDialogLiveData = MutableLiveData<Event<Unit>>()
    val hideDialogLiveData = MutableLiveData<Event<Unit>>()

    fun showMessage(message: String?) {
        messageLiveData.postValue(Event(message))
    }

    fun showLoader() {
        showDialogLiveData.postValue(Event(Unit))
    }

    fun hideLoader() {
        hideDialogLiveData.postValue(Event(Unit))
    }

    override fun onCleared() {
        cancelAllRequests()
        super.onCleared()
    }

    private fun cancelAllRequests() = coroutineContext.cancel()
}