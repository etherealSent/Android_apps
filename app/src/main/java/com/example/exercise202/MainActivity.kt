package com.example.exercise202


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.WorkerParameters
import com.example.exercise202.databinding.ActivityMainBinding
import com.example.exercise202.model.TVShow
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val tvShowAdapter by lazy {
        TVShowAdapter(object : TVShowAdapter.TVShowClickListener {
            override fun onTVShowClick(tvShow: TVShow) {
                openTVShowDetails(tvShow)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = tvShowAdapter

        val tvShowRepository = (application as TVApplication).repository
        val tvShowViewModel =
            ViewModelProvider(
                this, object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return TVShowViewModel(tvShowRepository) as T
                    }
                }
            )[TVShowViewModel::class.java]

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {

                launch {
                    tvShowViewModel.error.collect { error ->
                        if (error.isNotEmpty()) Snackbar
                            .make(recyclerView, error, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }
        binding.viewModel = tvShowViewModel
        binding.lifecycleOwner = this
    }

    private fun openTVShowDetails(tvShow: TVShow) {
        val intent = Intent(this, DetailsActivity::class.java).apply {
            putExtra(DetailsActivity.EXTRA_TITLE, tvShow.name)
            putExtra(DetailsActivity.EXTRA_OVERVIEW, tvShow.overview)
            putExtra(DetailsActivity.EXTRA_RELEASE, tvShow.firstAirDate)
            putExtra(DetailsActivity.EXTRA_POSTER, tvShow.posterPath)
        }
        startActivity(intent)
    }
}