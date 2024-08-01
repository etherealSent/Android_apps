package com.example.exercise202

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView

const val STAR_SIGN_ID = "STAR_SIGN_ID"

interface StarSignListener {
    fun onSelected(id: Int)
}

class MainActivity : AppCompatActivity(), StarSignListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            findViewById<FragmentContainerView>(
                R.id.fragment_container)?.let {
                    frameLayout ->
                val listFragment = ListFragment()
                supportFragmentManager
                    .beginTransaction()
                    .add(frameLayout.id, listFragment)
                    .commit()
            }
        }
    }

    override fun onSelected(starSignId: Int) {
        findViewById<FragmentContainerView>(R.id.fragment_container)?.let { frameLayout ->
            val detailFragment = DetailFragment.newInstance(starSignId)

            supportFragmentManager
                .beginTransaction()
                .replace(frameLayout.id, detailFragment)
                .addToBackStack(null)
                .commit()
        } }
}