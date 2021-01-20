package com.joanderson.mysubscribers.ui.subscriberlist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.joanderson.mysubscribers.R

class SubscriberListFragment : Fragment() {

    private lateinit var viewModel: SubscriberListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.subscriber_list_fragment, container, false)
    }


}