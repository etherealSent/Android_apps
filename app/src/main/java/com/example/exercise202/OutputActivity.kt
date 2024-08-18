package com.example.exercise202

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar

class OutputActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val firstVariant: TextView = findViewById(R.id.first_variant)
        val secondVariant: TextView = findViewById(R.id.second_variant)
        val thirdVariant: TextView = findViewById(R.id.third_variant)
        val copyButton: Button = findViewById(R.id.button)

        val length = intent.getIntExtra("length", 1)
        val isUppercase = intent.getBooleanExtra("isUppercase", false)
        val isNumbers = intent.getBooleanExtra("isNumbers", false)
        val isSpecialCharacters = intent.getBooleanExtra("isSpecialCharacters", false)

        val passwords = generatePassword(isUppercase, isNumbers, isSpecialCharacters, length)

        firstVariant.text = passwords[0]
        secondVariant.text = passwords[1]
        thirdVariant.text = passwords[2]

        copyButton.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val password = when {
                firstVariant.isVisible -> {
                    firstVariant.text.toString()
                }

                secondVariant.isVisible -> {
                    secondVariant.text.toString()
                }

                else -> {
                    thirdVariant.text.toString()
                }
            }

            val clip: ClipData = ClipData.newPlainText("password", password)
            clipboard.setPrimaryClip(clip)

            Snackbar.make(it, "Password has been copied!", Snackbar.LENGTH_SHORT).show()
        }

    }

    private fun generatePassword(
        useUppercase: Boolean,
        useNumbers: Boolean,
        useSpecialCharacters: Boolean,
        length: Int
    ): List<String> {
        val chars = mutableListOf<Char>()
        if (useUppercase) chars.addAll(('A'..'Z'))
        if (useNumbers) chars.addAll(('0'..'9'))
        if (useSpecialCharacters) chars.addAll("!@#\$%^&*()_+-=[]{};':\"\\|,.<>/?".toList())
        if (chars.isEmpty()) chars.addAll(('a'..'z'))

        return listOf(
            (1..length).map { chars.random() }.joinToString(""),
            (1..length).map { chars.random() }.joinToString(""),
            (1..length).map { chars.random() }.joinToString("")
        )
    }
}