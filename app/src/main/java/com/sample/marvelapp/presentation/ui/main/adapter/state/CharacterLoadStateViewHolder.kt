package com.sample.marvelapp.presentation.ui.main.adapter.state

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.sample.marvelapp.R

class CharacterLoadStateViewHolder(private val retry: () -> Unit, parent: ViewGroup):
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_loading_state, parent, false)
    ) {

    fun bind(state: LoadState) {
        val button = itemView.findViewById<Button>(R.id.button_state_retry)
        val progress = itemView.findViewById<ProgressBar>(R.id.progress_state_loading)

        button.setOnClickListener {
            retry.invoke()
        }

        itemView.apply {
            progress.isVisible = state is LoadState.Loading
            button.isVisible = state is LoadState.Error
        }
    }
}