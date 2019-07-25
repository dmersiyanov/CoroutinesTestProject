package com.dmity.courutinesotus.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dmity.courutinesotus.R
import com.dmity.courutinesotus.models.MovieDTO

class MoviesAdapter(private val clickListener: (item: MovieDTO) -> Unit) : RecyclerView.Adapter<MoviesViewHolder>() {

    private var items = listOf<MovieDTO>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false),
            clickListener = clickListener
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setItems(newItems: List<MovieDTO>) {
        items = newItems
        notifyDataSetChanged()
    }
}