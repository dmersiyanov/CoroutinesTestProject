package com.dmity.courutinesotus.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.dmity.courutinesotus.models.MovieDTO
import kotlinx.android.synthetic.main.item_movie.view.*


class MoviesViewHolder(
    itemView: View,
    private val clickListener: (item: MovieDTO) -> Unit
) :
    RecyclerView.ViewHolder(itemView) {

    fun bind(item: MovieDTO) {

        itemView.tvMovieTitle.text = item.title
        itemView.setOnClickListener { clickListener.invoke(item) }

    }

}