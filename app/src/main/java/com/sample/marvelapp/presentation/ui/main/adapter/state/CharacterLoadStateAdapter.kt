package com.sample.marvelapp.presentation.ui.main.adapter.state

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class CharacterLoadStateAdapter(private val retry: () -> Unit): LoadStateAdapter<CharacterLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: CharacterLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): CharacterLoadStateViewHolder {
        return CharacterLoadStateViewHolder(retry, parent)
    }
}