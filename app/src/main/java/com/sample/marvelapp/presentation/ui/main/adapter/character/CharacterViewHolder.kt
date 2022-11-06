package com.sample.marvelapp.presentation.ui.main.adapter.character

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sample.marvelapp.R
import com.sample.marvelapp.domain.model.Character

class CharacterViewHolder(private val onClick: (Int) -> Unit, itemView: View):
    RecyclerView.ViewHolder(itemView) {

    fun bind(character: Character?) {
        itemView.findViewById<TextView>(R.id.tv_item_title).text = character?.name
        itemView.findViewById<TextView>(R.id.tv_item_description).text = character?.description
        character?.let { ch ->
            bindImage(ch.thumbnail)
            itemView.setOnClickListener { onClick(ch.id) }
        }
    }

    private fun bindImage(url: String) {
        val iv = itemView.findViewById<ImageView>(R.id.iv_item_image)
        Glide.with(itemView.context).load(url).into(iv)
    }
}