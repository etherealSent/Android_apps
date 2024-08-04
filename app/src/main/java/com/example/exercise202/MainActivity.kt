package com.example.exercise202

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise202.model.Flavor
import com.example.exercise202.model.ListItemUiModel
import com.example.exercise202.model.RecipeUiModel

class MainActivity : AppCompatActivity() {

    private val recyclerView: RecyclerView by lazy {
        findViewById(R.id.recycler_view)
    }

    private val listItemsAdapter by lazy {
        ListItemAdapter(
            layoutInflater = layoutInflater,
            onClickListener = object : ListItemAdapter.OnClickListener {
                override fun onItemClick(recipe: RecipeUiModel) = showSelectionDialog(recipe)
            }
        )
    }

    private val titleField: EditText by lazy {
        findViewById(R.id.title_field)
    }

    private val descriptionField: EditText by lazy {
        findViewById(R.id.description_field)
    }

    private val savoryButton: View by lazy {
        findViewById(R.id.savory_button)
    }

    private val sweetButton: View by lazy {
        findViewById(R.id.sweet_button)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.adapter = listItemsAdapter
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val itemTouchHelper = ItemTouchHelper(listItemsAdapter.swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        savoryButton.setOnClickListener {
            listItemsAdapter.addRecipe(
                ListItemUiModel.Recipe(
                    RecipeUiModel(
                        title = titleField.text.toString(),
                        description = descriptionField.text.toString(),
                        flavor = Flavor.SAVORY
                    )
                )
            )
        }

        sweetButton.setOnClickListener {
            listItemsAdapter.addRecipe(
                ListItemUiModel.Recipe(
                    RecipeUiModel(
                        title = titleField.text.toString(),
                        description = descriptionField.text.toString(),
                        flavor = Flavor.SWEET
                    )
                )
            )
        }
    }
    private fun showSelectionDialog(recipeData: RecipeUiModel) {
        AlertDialog.Builder(this)
            .setTitle(recipeData.title)
            .setMessage(recipeData.description)
            .setPositiveButton("OK") { _, _ -> }
            .show()
    }

}