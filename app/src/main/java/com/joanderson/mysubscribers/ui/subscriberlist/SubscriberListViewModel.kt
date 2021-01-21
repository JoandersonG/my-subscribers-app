package com.joanderson.mysubscribers.ui.subscriberlist

import androidx.lifecycle.ViewModel
import com.joanderson.mysubscribers.repository.SubscriberRepository

class SubscriberListViewModel(
        private val repository: SubscriberRepository
) : ViewModel() {
    val allSubscribersEvent = repository.getAllSubscribers()
}