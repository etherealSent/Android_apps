package com.example.exercise202

import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.exercise202.worker.CatFurGroomingWorker
import com.example.exercise202.worker.CatLitterBoxSittingWorker
import com.example.exercise202.worker.CatStretchingWorker
import com.example.exercise202.worker.CatSuitUpWorker

class MainActivity : AppCompatActivity() {

    private val workManager = WorkManager.getInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val networkConstraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val catAgentId = "CatAgent1"

        val catStretchingRequest = OneTimeWorkRequest.
        Builder(CatLitterBoxSittingWorker::class.java)
            .setConstraints(networkConstraints)
            .setInputData(
                getCatAgentIdInputData(
                    CatStretchingWorker
                    .INPUT_DATA_CAT_AGENT_ID, catAgentId)
            ).build()

        val catFurGroomingRequest = OneTimeWorkRequest.
        Builder(CatFurGroomingWorker::class.java)
            .setConstraints(networkConstraints)
            .setInputData(getCatAgentIdInputData(
                CatFurGroomingWorker.INPUT_DATA_CAT_AGENT_ID,
                catAgentId)
            ).build()

        val catLitterBoxSittingRequest = OneTimeWorkRequest.
        Builder(CatLitterBoxSittingWorker::class.java)
            .setConstraints(networkConstraints)
            .setInputData(getCatAgentIdInputData(
                CatLitterBoxSittingWorker.INPUT_DATA_CAT_AGENT_ID,
                catAgentId)
            ).build()

        val catSuitUpRequest = OneTimeWorkRequest.Builder(
            CatSuitUpWorker::class.java
        ).setConstraints(networkConstraints)
            .setInputData(getCatAgentIdInputData(CatSuitUpWorker.
            INPUT_DATA_CAT_AGENT_ID, catAgentId)
            ).build()

        workManager.beginWith(catStretchingRequest)
            .then(catFurGroomingRequest)
            .then(catLitterBoxSittingRequest)
            .then(catSuitUpRequest)
            .enqueue()

        workManager.getWorkInfoByIdLiveData(
            catStretchingRequest.id).observe(this) { info ->
            if (info.state.isFinished) {
                showResult("Agent done stretching")
            } }
        workManager.getWorkInfoByIdLiveData(
            catFurGroomingRequest.id).observe(this) { info ->
            if (info.state.isFinished) {
                showResult("Agent done grooming its fur")
            } }
        workManager.getWorkInfoByIdLiveData( catLitterBoxSittingRequest.id).observe(this) { info ->
            if (info.state.isFinished) {
                showResult("Agent done sitting in litter box")
            } }
        workManager.getWorkInfoByIdLiveData(catSuitUpRequest.id).observe(this) { info ->
            if (info.state.isFinished) {
                showResult("Agent done suiting up. Ready to go!")
            }
        }
    }

    private fun getCatAgentIdInputData(
        catAgentIdKey: String, catAgentIdValue: String) =
        Data.Builder()
            .putString(catAgentIdKey, catAgentIdValue).build()

    private fun showResult(message: String) {
        Toast.makeText(this, message, LENGTH_SHORT).show()
    }
}