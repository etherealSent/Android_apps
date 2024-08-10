package com.example.exercise202

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class Activity1 : AppCompatActivity() {

    private val button: View by lazy {
        findViewById(R.id.activity1_button)
    }

    private val numberEditText: EditText by lazy {
        findViewById(R.id.numberEdit)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actvity1)

        button.setOnClickListener {
            startActivity(Activity2.newIntent(this, numberEditText.text.toString().toInt()))
        }

    }
}