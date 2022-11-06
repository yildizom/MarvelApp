package com.sample.marvelapp.presentation.ui.detail.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sample.marvelapp.R
import com.sample.marvelapp.domain.model.Comic

class ComicViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(comic: Comic?) {
        itemView.findViewById<TextView>(R.id.tv_item_comic_title).text = comic?.title
        comic?.let { bindImage(it.thumbnail) }
    }

    private fun bindImage(url: String) {
        val iv = itemView.findViewById<ImageView>(R.id.iv_item_comic_image)
        Glide.with(itemView.context).load(url).into(iv)
    }
}