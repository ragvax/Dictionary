package com.ragvax.dictionary.ui.definition.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ragvax.dictionary.data.Definition
import com.ragvax.dictionary.databinding.ItemDefinitionBinding
import com.ragvax.dictionary.utils.hide

class DefinitionAdapter(
    private val definitions: List<Definition>,
) : RecyclerView.Adapter<DefinitionAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemDefinitionBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(definition: Definition, position: Int) {
                binding.tvDefinition.text = "$position. ${definition.definition}"
                if (definition.example != null) {
                    binding.tvDefinitionExample.text = definition.example
                } else {
                    binding.tvDefinitionExample.hide()
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemDefinitionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(definitions[position], position + 1)
    }

    override fun getItemCount(): Int = definitions.size
}