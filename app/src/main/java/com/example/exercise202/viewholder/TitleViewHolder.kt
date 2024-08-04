package com.example.exercise202.viewholder

import android.view.View
import android.widget.TextView
import com.example.exercise202.R
import com.example.exercise202.model.ListItemUiModel

class TitleViewHolder(
    containerView: View
) : ListItemViewHolder(containerView) {
    private val titleView: TextView by lazy {
        containerView.findViewById(R.id.item_title_title)
    }
    override fun bindData(listItem: ListItemUiModel) {
        require(listItem is ListItemUiModel.Title)
        { "Expected ListItemUiModel.Title" }
        titleView.text = listItem.title
    }
}