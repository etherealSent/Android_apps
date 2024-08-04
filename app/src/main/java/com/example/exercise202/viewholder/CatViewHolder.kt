package com.example.exercise202.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.exercise202.ImageLoader
import com.example.exercise202.R
import com.example.exercise202.model.CatBreed
import com.example.exercise202.model.CatUiModel
import com.example.exercise202.model.Gender
import com.example.exercise202.model.ListItemUiModel

val FEMALE_SYMBOL = "\u2640"
val MALE_SYMBOL = "\u2642"
const val UNKNOWN_SYMBOL = "?"

class CatViewHolder(
    private val containerView: View,
    private val imageLoader: ImageLoader,
    private val onClickListener: OnClickListener
) : ListItemViewHolder(containerView) {

    private val catBiographyView: TextView by lazy {
        containerView.findViewById(R.id.item_cat_biography) }
    private val catBreedView: TextView by lazy {
        containerView.findViewById(R.id.item_cat_breed) }
    private val catGenderView: TextView by lazy {
        containerView.findViewById(R.id.item_cat_gender) }
    private val catNameView: TextView by lazy {
        containerView.findViewById(R.id.item_cat_name) }
    private val catPhotoView: ImageView by lazy {
        containerView.findViewById(R.id.item_cat_photo)
    }

    override fun bindData(listItem: ListItemUiModel) {

        require(listItem is ListItemUiModel.Cat)
        { "Expected ListItemUiModel.Cat" }
        val cat = listItem.cat

        containerView.setOnClickListener {
            onClickListener.onClick(cat)
        }

        imageLoader.loadImage(cat.imageUrl, catPhotoView)
        catNameView.text = cat.name
        catBreedView.text = when (cat.breed) {
            CatBreed.AmericanCurl -> "American Curl"
            CatBreed.BalineseJavanese -> "Balinese-Javanese"
            CatBreed.ExoticShorthair -> "Exotic Shorthair"
        }
        catBiographyView.text = cat.biography
        catGenderView.text = when (cat.gender) {
            Gender.Female -> FEMALE_SYMBOL
            Gender.Male -> MALE_SYMBOL
            else -> UNKNOWN_SYMBOL
        }
    }

    interface OnClickListener {
        fun onClick(cat: CatUiModel)
    }
}