package com.example.recyclewview

import com.example.recyclewview.adapters.MyAdapter
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: MyAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val items = listOf("Item1", "Item2", "Item3", "Item4")
        adapter = MyAdapter(items) { item ->
            Toast.makeText(this, "Clicked: $item", Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = adapter

        val updateButton: Button = findViewById(R.id.updateButton)
        updateButton.setOnClickListener {
            val newItems = listOf("New Item1", "New Item2", "New Item3")
            adapter.updateData(newItems)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.recyclerView)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}

