package com.ragvax.dictionary.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ragvax.dictionary.data.local.RecentQuery
import com.ragvax.dictionary.databinding.ItemRecentSearchesBinding
import java.util.*

class RecentSearchesAdapter(
    private val listener: OnRecentSearchesItemClickListener,
) : ListAdapter<RecentQuery, RecentSearchesAdapter.ViewHolder>(RecentSearchesDiffCallback()) {

    inner class ViewHolder(
        private val binding: ItemRecentSearchesBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val searchQuery = getItem(position)
                    listener.onRecentSearchesItemClick(searchQuery.queryText)
                }
            }
        }
        fun bind(recentQuery: RecentQuery) {
            binding.tvSearchQuery.text = recentQuery.queryText.capitalize(Locale.ROOT)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRecentSearchesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class RecentSearchesDiffCallback : DiffUtil.ItemCallback<RecentQuery>() {
        override fun areItemsTheSame(oldItem: RecentQuery, newItem: RecentQuery): Boolean =
            oldItem.queryText == newItem.queryText

        override fun areContentsTheSame(oldItem: RecentQuery, newItem: RecentQuery): Boolean =
            oldItem == newItem
    }

    interface OnRecentSearchesItemClickListener {
        fun onRecentSearchesItemClick(query: String)
    }
}