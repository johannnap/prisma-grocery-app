package com.prisma.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button
import com.prisma.app.R

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: GroceryAdapter
    private val masterList = mutableListOf(
        GroceryItem("Maito"),
        GroceryItem("Tomaatti"),
        GroceryItem("Leipä"),
        GroceryItem("Juusto"),
        GroceryItem("Pasta"),
        GroceryItem("Kahvi"),
        GroceryItem("Voi")
    )
    private val shoppingList = mutableListOf<GroceryItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_App_Dark)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val addButton: Button = findViewById(R.id.addButton)

        adapter = GroceryAdapter(shoppingList) { position ->
            shoppingList.removeAt(position)
            adapter.notifyItemRemoved(position)
        }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        addButton.setOnClickListener {
            val selectedItems = masterList.filter { it.isSelected }.map {
                GroceryItem(it.name, 1)
            }
            shoppingList.clear()
            shoppingList.addAll(selectedItems)
            adapter.notifyDataSetChanged()
        }
    }
}
