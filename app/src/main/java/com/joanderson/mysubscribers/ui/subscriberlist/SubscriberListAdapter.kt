package com.joanderson.mysubscribers.ui.subscriberlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joanderson.mysubscribers.R
import com.joanderson.mysubscribers.data.db.entity.SubscriberEntity
import com.joanderson.mysubscribers.databinding.SubscriberItemBinding

class SubscriberListAdapter(
    private val subscribers: List<SubscriberEntity>
) :
    RecyclerView.Adapter<SubscriberListAdapter.SubscriberListViewHolder>() {

    var onItemClick: ((entity: SubscriberEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriberListViewHolder {
        return SubscriberListViewHolder.from(parent, onItemClick)
    }

    override fun onBindViewHolder(holder: SubscriberListViewHolder, position: Int) {
        holder.bindView(subscribers[position])
    }

    override fun getItemCount() = subscribers.size

    class SubscriberListViewHolder private constructor(
            private val binding: SubscriberItemBinding,
            private val onItemClick: ((entity: SubscriberEntity) -> Unit)?
            )
        : RecyclerView.ViewHolder(binding.root) {

        fun bindView(subscriber: SubscriberEntity) {
            binding.subscriber = subscriber
            // make sure to include this so your view will be updated
            binding.executePendingBindings()
            itemView.setOnClickListener {
                onItemClick?.invoke(subscriber )
            }
        }

        companion object {
            fun from(parent: ViewGroup, onItemClick: ((entity: SubscriberEntity) -> Unit)?): SubscriberListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = SubscriberItemBinding.inflate(layoutInflater, parent, false)

                return SubscriberListViewHolder(binding, onItemClick)
            }
        }
    }
}