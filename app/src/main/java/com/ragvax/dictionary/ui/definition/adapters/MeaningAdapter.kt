package com.ragvax.dictionary.ui.definition.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ragvax.dictionary.data.Meaning
import com.ragvax.dictionary.databinding.ItemMeaningBinding
import com.ragvax.dictionary.utils.hide

class MeaningAdapter(
    private val meanings: List<Meaning>,
    private val context: Context,
) : ListAdapter<Meaning, MeaningAdapter.ViewHolder>(MeaningDiffCallback()) {

    inner class ViewHolder(private val binding: ItemMeaningBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(meaning: Meaning) {
            binding.tvPartOfSpeech.text = "${meaning.partOfSpeech}."
            if (meaning.definitions != null) {
                binding.rvDefinition.adapter = DefinitionAdapter(meaning.definitions)
                binding.rvDefinition.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            } else {
                binding.rvDefinition.hide()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMeaningBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(meanings[position])
    }

    override fun getItemCount(): Int = meanings.size

    class MeaningDiffCallback : DiffUtil.ItemCallback<Meaning>() {
        override fun areItemsTheSame(oldItem: Meaning, newItem: Meaning): Boolean =
            oldItem.partOfSpeech == newItem.partOfSpeech

        override fun areContentsTheSame(oldItem: Meaning, newItem: Meaning): Boolean =
            oldItem == newItem
    }
}