package com.example.exercise202

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val lengthText: EditText = findViewById(R.id.length_text)

        val uppercaseCheck: CheckBox = findViewById(R.id.special_characters_checkbox)
        val numbersCheck: CheckBox = findViewById(R.id.numbers_checkbox)
        val specialCharactersCheck: CheckBox = findViewById(R.id.special_characters_checkbox)

        val generateButton: Button = findViewById(R.id.generate_button)

        generateButton.setOnClickListener {
            val length = lengthText.text.toString().ifBlank { "0" }
            val isUppercase = uppercaseCheck.isChecked
            val isNumbers = numbersCheck.isChecked
            val isSpecialCharacters = specialCharactersCheck.isChecked

            val intent = Intent(this, OutputActivity::class.java).apply {
                putExtra("length", length.toInt())
                putExtra("isUppercase", isUppercase)
                putExtra("isNumbers", isNumbers)
                putExtra("isSpecialCharacters", isSpecialCharacters)
            }

            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }
    }
}