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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriberListViewHolder {
        //val view = LayoutInflater.from(parent.context).inflate(R.layout.subscriber_item, parent, false)
        return SubscriberListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SubscriberListViewHolder, position: Int) {
        holder.bindView(subscribers[position])
    }

    override fun getItemCount() = subscribers.size

    class SubscriberListViewHolder private constructor(private val binding: SubscriberItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bindView(subscriber: SubscriberEntity) {
            binding.subscriber = subscriber
            // make sure to include this so your view will be updated
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): SubscriberListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = SubscriberItemBinding.inflate(layoutInflater, parent, false)

                return SubscriberListViewHolder(binding)
            }
        }
    }

//    class SubscriberListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        private val textViewSubscribeName: TextView = itemView.text_subscriber_name
//        private val textViewSubscribeEmail: TextView = itemView.text_subscriber_email
//
//        fun bindView(subscriber: SubscriberEntity) {
//            textViewSubscribeName.text = subscriber.name
//            textViewSubscribeEmail.text = subscriber.email
//        }
//    }
}