package com.example.exercise202

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetailsActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TITLE = "title"
        const val EXTRA_RELEASE = "release"
        const val EXTRA_OVERVIEW = "overview"
        const val EXTRA_POSTER = "poster"
        const val IMAGE_URL = "https://image.tmdb.org/t/p/w185/"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val nameTextView = findViewById<TextView>(R.id.details_name)
        val firstAirDateTextView = findViewById<TextView>(R.id.details_first_air_date_year)
        val overviewTextView = findViewById<TextView>(R.id.details_overview)
        val posterImageView = findViewById<ImageView>(R.id.details_poster)

        val extras = intent.extras

        nameTextView.text = extras?.getString(EXTRA_TITLE).orEmpty()
        firstAirDateTextView.text = extras?.getString(EXTRA_RELEASE).orEmpty()
        overviewTextView.text = getString(R.string.tv_show_overview,extras?.getString(EXTRA_OVERVIEW).orEmpty())

        val posterPath = extras?.getString(EXTRA_POSTER).orEmpty()

        Glide.with(this@DetailsActivity)
            .load("$IMAGE_URL$posterPath")
            .placeholder(R.mipmap.ic_launcher)
            .fitCenter()
            .into(posterImageView)
    }
}