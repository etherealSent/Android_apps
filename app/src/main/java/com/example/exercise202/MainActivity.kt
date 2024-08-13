package com.example.exercise202

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as RandomApplication).applicationComponent.createMainSubcomponent()
            .inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        viewModel.numberLiveData.observe(
            this
        ) {
            findViewById<TextView>(R.id.activity_main_text_view).text = it.toString()
        }
        findViewById<TextView>(R.id.activity_main_button).setOnClickListener {
            viewModel.generateNextNumber()
        }
    }
}