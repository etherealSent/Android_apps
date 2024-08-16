package com.example.exercise202

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TVShowWorker(private val context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        val tvShowsRepository = (context as TVApplication).repository

        CoroutineScope(Dispatchers.IO).launch {
            tvShowsRepository.fetchTVShowsFromNetwork()
        }

        return Result.success()
    }
}