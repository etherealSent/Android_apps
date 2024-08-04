package com.example.exercise202

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise202.model.Flavor
import com.example.exercise202.model.ListItemUiModel
import com.example.exercise202.model.RecipeUiModel
import com.example.exercise202.viewholder.FlavorViewHolder
import com.example.exercise202.viewholder.ListItemViewHolder
import com.example.exercise202.viewholder.RecipeViewHolder

private const val VIEW_TYPE_TITLE = 0
private const val VIEW_TYPE_RECIPE = 1

class ListItemAdapter(
    private val layoutInflater: LayoutInflater,
    private val onClickListener: OnClickListener
    ): RecyclerView.Adapter<ListItemViewHolder>() {

        private val sweetFlavorTitle = ListItemUiModel.Title(Flavor.SWEET)
        private val savoryFlavorTitle = ListItemUiModel.Title(Flavor.SAVORY)

        val swipeToDeleteCallback = SwipeToDeleteCallback()

        private val listData = mutableListOf<ListItemUiModel>(
            sweetFlavorTitle,
            savoryFlavorTitle
        )

    fun addRecipe(recipe: ListItemUiModel.Recipe) {
        val insertionIndex = listData.indexOf(when(recipe.recipe.flavor) {
            Flavor.SWEET -> sweetFlavorTitle
            Flavor.SAVORY -> savoryFlavorTitle
        }) + 1

        listData.add(insertionIndex, recipe)
        notifyItemInserted(insertionIndex)
    }

    fun removeRecipe(position: Int) {
        listData.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder =
        when (viewType) {
            VIEW_TYPE_TITLE -> {
                val view = layoutInflater.inflate(
                    R.layout.item_flavor_title, parent, false)
                FlavorViewHolder(view)
            }
            VIEW_TYPE_RECIPE -> {
                val view = layoutInflater.inflate(
                    R.layout.item_recipe, parent, false)
                RecipeViewHolder(
                    view,
                    object : RecipeViewHolder.OnClickListener {
                        override fun onClick(recipe: RecipeUiModel) =
                            onClickListener.onItemClick(recipe)
                    })
            }
            else -> throw IllegalArgumentException("Unknown view type requested: $viewType")
        }

    override fun getItemViewType(position: Int) =
        when (listData[position]) {
            is ListItemUiModel.Title -> VIEW_TYPE_TITLE
            is ListItemUiModel.Recipe -> VIEW_TYPE_RECIPE
        }

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.bindData(listData[position])
    }

    interface OnClickListener {
        fun onItemClick(recipe: RecipeUiModel)
    }

    inner class SwipeToDeleteCallback : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = false

        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ) = if (viewHolder is RecipeViewHolder) {
            makeMovementFlags(
                ItemTouchHelper.ACTION_STATE_IDLE,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) or makeMovementFlags(
                ItemTouchHelper.ACTION_STATE_SWIPE,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            )
        } else { 0 }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            removeRecipe(position)
        }

    }
}