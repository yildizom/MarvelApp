package com.sample.marvelapp.presentation.ui.main.adapter.character

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.sample.marvelapp.R
import com.sample.marvelapp.domain.model.Character

class CharacterPagingAdapter(private val onClickItem: (id: Int) -> Unit):
    PagingDataAdapter<Character, CharacterViewHolder>(diffCallback) {
    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(onClickItem, view)
    }

    companion object {
        val diffCallback = object: DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem == newItem
            }
        }
    }
}