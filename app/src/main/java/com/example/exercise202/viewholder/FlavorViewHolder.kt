package com.example.exercise202.viewholder

import android.view.View
import android.widget.TextView
import com.example.exercise202.R
import com.example.exercise202.model.ListItemUiModel
import com.example.exercise202.model.RecipeUiModel

class FlavorViewHolder(containerView: View) : ListItemViewHolder(containerView) {

    private val title: TextView by lazy {
        containerView.findViewById(R.id.flavor_title)
    }

    override fun bindData(listItem: ListItemUiModel) {

        require(listItem is ListItemUiModel.Title)
        { "Expected ListItemUiModel.Title" }

        val flavor = listItem.title

        title.text = flavor.title
    }

}