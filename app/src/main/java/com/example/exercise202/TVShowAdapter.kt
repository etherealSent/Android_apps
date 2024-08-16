package com.example.exercise202

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.exercise202.model.TVShow

class TVShowAdapter(
    private val clickListener: TVShowClickListener
) : RecyclerView.Adapter<TVShowAdapter.TVShowViewHolder>() {

    private val tvShows = mutableListOf<TVShow>()

    fun setTVShows(newTVShows: List<TVShow>) {
        tvShows.clear()
        this.tvShows.addAll(newTVShows)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowViewHolder {
        return TVShowViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_tv_show_item, parent, false))
    }

    override fun getItemCount(): Int = tvShows.size

    override fun onBindViewHolder(holder: TVShowViewHolder, position: Int) {
        holder.bindItem(tvShows[position])
        holder.itemView.setOnClickListener { clickListener.onTVShowClick(tvShows[position]) }
    }

    inner class TVShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageUrl = "https://image.tmdb.org/t/p/w185/"

        private val title: TextView by lazy {
            itemView.findViewById(R.id.tv_film_item_title)
        }

        private val poster: ImageView by lazy {
            itemView.findViewById(R.id.tv_film_item_poster)

        }

        fun bindItem(tvShow: TVShow) {
            title.text = tvShow.name

            Glide.with(itemView.context)
                .load("$imageUrl${tvShow.posterPath}")
                .placeholder(R.mipmap.ic_launcher)
                .fitCenter()
                .into(poster)
        }
    }

    interface TVShowClickListener {
        fun onTVShowClick(tvShow: TVShow)
    }
}