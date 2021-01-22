package com.joanderson.mysubscribers.ui.subscriber

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joanderson.mysubscribers.R
import com.joanderson.mysubscribers.repository.SubscriberRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class SubscriberViewModel(
    private val repository: SubscriberRepository
) : ViewModel() {

    private val _subscriberStateEventData = MutableLiveData<SubscriberState>()
    val subscriberStateEventData: LiveData<SubscriberState>
        get() = _subscriberStateEventData

    private val _messageEventData = MutableLiveData<Int>()
    val messageEventData: LiveData<Int>
        get() = _messageEventData

    fun addOrUpdateSubscriber(name: String, email: String, id: Long = 0) {
        if (id > 0) {
            updateSubscriber(id, name, email)
        } else {
            insertSubscriber(name, email)
        }
    }

    private fun updateSubscriber(id: Long, name: String, email: String) = viewModelScope.launch {
        try {
            repository.updateSubscriber(id, name, email)
            _subscriberStateEventData.value = SubscriberState.Updated
            _messageEventData.value = R.string.subscriber_updated_successfully
        } catch (e: Exception) {
            _messageEventData.value = R.string.subscriber_error_to_update
            Log.e(TAG, e.toString())
        }
    }

    private fun insertSubscriber(name: String, email: String) = viewModelScope.launch {
        try {
            val id  = repository.insertSubscriber(name, email)
            if (id > 0) {
                _subscriberStateEventData.value = SubscriberState.Inserted
                _messageEventData.value = R.string.subscriber_inserted_successfully
            }
        } catch (e: Exception) {
            _messageEventData.value = R.string.subscriber_error_to_insert
            Log.e("", e.toString())
        }
    }

    fun removeSubscriber(id: Long) = viewModelScope.launch {
        try {
            if (id > 0) {
                repository.deleteSubscriber(id)
                _subscriberStateEventData.value = SubscriberState.Deleted
                _messageEventData.value = R.string.subscriber_deleted_successfully
            }
        } catch (e: Exception) {
            _messageEventData.value = R.string.subscriber_error_to_delete
            Log.e(TAG, e.toString())
        }
    }

    sealed class SubscriberState {
        object Inserted: SubscriberState()
        object Updated: SubscriberState()
        object Deleted: SubscriberState()
    }

    companion object {
        private val TAG = SubscriberViewModel::class.java.simpleName
    }
}