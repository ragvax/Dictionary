package com.ragvax.dictionary.ui.definition.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ragvax.dictionary.data.source.remote.Meaning
import com.ragvax.dictionary.databinding.ItemMeaningBinding
import com.ragvax.dictionary.domain.model.WordDefinition
import com.ragvax.dictionary.utils.hide

class MeaningAdapter(
    private val meanings: List<WordDefinition.Meaning>,
    private val context: Context,
) : ListAdapter<WordDefinition.Meaning, MeaningAdapter.ViewHolder>(MeaningDiffCallback()) {

    inner class ViewHolder(private val binding: ItemMeaningBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(meaning: WordDefinition.Meaning) {
            binding.tvPartOfSpeech.text = meaning.partOfSpeech
            if (meaning.definitions.isNotEmpty()) {
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

    class MeaningDiffCallback : DiffUtil.ItemCallback<WordDefinition.Meaning>() {
        override fun areItemsTheSame(oldItem: WordDefinition.Meaning, newItem: WordDefinition.Meaning): Boolean =
            oldItem.partOfSpeech == newItem.partOfSpeech

        override fun areContentsTheSame(oldItem: WordDefinition.Meaning, newItem: WordDefinition.Meaning): Boolean =
            oldItem == newItem
    }
}