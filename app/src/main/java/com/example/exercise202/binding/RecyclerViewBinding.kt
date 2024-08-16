package com.example.exercise202.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise202.TVShowAdapter
import com.example.exercise202.model.TVShow

@BindingAdapter("list")
fun bindTVShows(view: RecyclerView, tvShows: List<TVShow>?) {
    val adapter = view.adapter as TVShowAdapter
    adapter.setTVShows(tvShows ?: emptyList())
}