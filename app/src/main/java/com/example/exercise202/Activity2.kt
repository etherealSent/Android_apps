package com.example.exercise202

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Activity2 : AppCompatActivity() {

    companion object {
        const val AMOUNT_OF_ITEMS = "amount_of_items"
        fun newIntent(context: Context, itemsSize: Int) =
            Intent(context, Activity2::class.java)
                .putExtra(AMOUNT_OF_ITEMS, itemsSize)
    }

    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity2)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = ItemAdapter(LayoutInflater.from(this)) {
            startActivity(Activity3.newIntent(this, it))
        }

        recyclerView.adapter = adapter

        val itemListSize = intent.getIntExtra(AMOUNT_OF_ITEMS, 0)
        (application as MyApplication).itemGenerator.generateItemsAsync(itemListSize) {
            runOnUiThread {
                adapter.addItems(it)
            }
        }

    }

}

