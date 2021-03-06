package com.joanderson.mysubscribers.ui.subscriber

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.joanderson.mysubscribers.R
import com.joanderson.mysubscribers.data.db.AppDatabase
import com.joanderson.mysubscribers.databinding.SubscriberFragmentBinding
import com.joanderson.mysubscribers.extension.hideKeyboard
import com.joanderson.mysubscribers.repository.DatabaseDataSource
import com.joanderson.mysubscribers.repository.SubscriberRepository

class SubscriberFragment : Fragment() {

    private var _binding: SubscriberFragmentBinding? = null
    private val binding get() = _binding!!
    private val args: SubscriberFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SubscriberFragmentBinding.inflate(inflater, container,false)
        return binding.root
    }

    private val viewModel: SubscriberViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val subscriberDAO = AppDatabase.getInstance(requireContext()).subscriberDAO
                val repository: SubscriberRepository = DatabaseDataSource(subscriberDAO)
                return SubscriberViewModel(repository) as T
            }

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.subscriber?.let { subscriber ->
            binding.buttonSubscriber.text = getString(R.string.subscriber_button_update)
            binding.inputName.setText(subscriber.name)
            binding.inputEmail.setText(subscriber.email)
            binding.buttonDelete.visibility = View.VISIBLE
        }

        observeEvents()
        setListeners()
    }

    private fun observeEvents() {
        viewModel.subscriberStateEventData.observe(viewLifecycleOwner) { subscriberState ->
            when (subscriberState) {
                is SubscriberViewModel.SubscriberState.Inserted,
                is SubscriberViewModel.SubscriberState.Updated,
                is SubscriberViewModel.SubscriberState.Deleted -> {
                    clearFields()
                    hideKeyboard()
                    findNavController().popBackStack()
                }
            }
        }
        viewModel.messageEventData.observe(viewLifecycleOwner) {stringResId ->
            Snackbar.make(requireView(), stringResId, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun clearFields() {
        binding.inputName.setText("")
        binding.inputEmail.setText("")
    }

    private fun hideKeyboard() {
        val parentActivity = requireActivity()
        if (parentActivity is AppCompatActivity) {
            parentActivity.hideKeyboard()
        }
    }

    private fun setListeners() {
         binding.buttonSubscriber.setOnClickListener {
             val name = binding.inputName.text.toString()
             val email = binding.inputEmail.text.toString()
             viewModel.addOrUpdateSubscriber(name, email, args.subscriber?.id ?: 0)

         }
        binding.buttonDelete.setOnClickListener{
            viewModel.removeSubscriber(args.subscriber?.id ?: 0)
        }
    }

}