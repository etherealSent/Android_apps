package com.example.exercise202

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise202.api.Post

class MainAdapter(
    private val layoutInflater: LayoutInflater,
) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private val posts = mutableListOf<Post>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(layoutInflater.inflate(R.layout.view_post_row, parent, false))
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    fun updatePosts(newPosts: List<Post>) {
        posts.clear()
        posts.addAll(newPosts)
        notifyDataSetChanged()
    }

    inner class MainViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView) {

        private val title = containerView.findViewById<TextView>(R.id.title)
        private val id = containerView.findViewById<TextView>(R.id.postId)
        private val userId = containerView.findViewById<TextView>(R.id.userId)
        private val body = containerView.findViewById<TextView>(R.id.body)

        fun bind(post: Post) {
            title.text = post.title
            id.text = post.id.toString()
            userId.text = post.userId.toString()
            body.text = post.body
        }
    }
}