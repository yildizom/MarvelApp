package com.sample.marvelapp.presentation.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.sample.marvelapp.R
import com.sample.marvelapp.domain.model.Comic

class ComicPagingAdapter: PagingDataAdapter<Comic, ComicViewHolder>(diffCallback) {
    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_comic, parent, false)
        return ComicViewHolder(view)
    }

    companion object {
        val diffCallback = object: DiffUtil.ItemCallback<Comic>() {
            override fun areItemsTheSame(oldItem: Comic, newItem: Comic): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Comic, newItem: Comic): Boolean {
                return oldItem == newItem
            }
        }
    }
}