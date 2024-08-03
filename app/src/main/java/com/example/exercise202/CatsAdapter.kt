package com.example.exercise202

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise202.model.CatUiModel


class CatsAdapter(
    private val layoutInflater: LayoutInflater,
    private val imageLoader: ImageLoader,
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<CatViewHolder>() {

    private val cats = mutableListOf<CatUiModel>()

    fun setData(newCats: List<CatUiModel>) {
        cats.clear()
        cats.addAll(newCats)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view = layoutInflater.inflate(R.layout.item_cat, parent, false)
        return CatViewHolder(view, imageLoader, object :
        CatViewHolder.OnClickListener {
            override fun onClick(cat: CatUiModel) =
                onClickListener.onItemClick(cat)
        })
    }

    override fun getItemCount() = cats.size

    override fun onBindViewHolder(
        holder: CatViewHolder, position: Int) {
        holder.bindData(cats[position])
    }

    interface OnClickListener {
        fun onItemClick(cat: CatUiModel)
    }

}
