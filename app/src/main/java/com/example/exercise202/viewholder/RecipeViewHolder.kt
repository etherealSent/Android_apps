package com.example.exercise202.viewholder

import android.view.View
import android.widget.TextView
import com.example.exercise202.model.ListItemUiModel
import com.example.exercise202.model.RecipeUiModel
import com.example.exercise202.R

class RecipeViewHolder(
    private val containerView: View,
    private val onClickListener: OnClickListener
) : ListItemViewHolder(containerView) {

    private val title: TextView by lazy {
        containerView.findViewById(R.id.recipe_title)
    }

    override fun bindData(listItem: ListItemUiModel) {

        require(listItem is ListItemUiModel.Recipe)
        { "Expected ListItemUiModel.Recipe" }

        val recipe = listItem.recipe

        containerView.setOnClickListener {
            onClickListener.onClick(recipe)
        }

        title.text = recipe.title
    }

    interface OnClickListener {
        fun onClick(recipe: RecipeUiModel)
    }
}