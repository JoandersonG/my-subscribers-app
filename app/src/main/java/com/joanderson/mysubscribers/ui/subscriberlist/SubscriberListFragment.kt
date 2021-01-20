package com.joanderson.mysubscribers.ui.subscriberlist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.joanderson.mysubscribers.R
import com.joanderson.mysubscribers.data.db.entity.SubscriberEntity
import com.joanderson.mysubscribers.databinding.SubscriberListFragmentBinding

class SubscriberListFragment : Fragment() {

    private lateinit var viewModel: SubscriberListViewModel
    private var _binding: SubscriberListFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SubscriberListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val subscriberListAdapter = SubscriberListAdapter(
            listOf(
                SubscriberEntity(1, "Joanderson", "joanderson@contato.com"),
                SubscriberEntity(1, "Joan Marcos", "joan.marcos@contato.com")
            )
        )
        binding.recyclerSubscribers.run {
            setHasFixedSize(true)
            adapter = subscriberListAdapter
        }

    }


}