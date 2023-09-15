package com.example.wishlist2

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val wishlistItems = ArrayList<WishlistItem>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var wishlistAdapter: WishlistAdapter
    private lateinit var editTextItemName: EditText
    private lateinit var editTextItemPrice: EditText
    private lateinit var editTextItemURL: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        editTextItemName = findViewById(R.id.editTextItemName)
        editTextItemPrice = findViewById(R.id.editTextItemPrice)
        editTextItemURL = findViewById(R.id.editTextItemURL)

        // Initialize the RecyclerView and adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        wishlistAdapter = WishlistAdapter(wishlistItems)
        recyclerView.adapter = wishlistAdapter

        val buttonAddItem: Button = findViewById(R.id.buttonAddItem)
        buttonAddItem.setOnClickListener {
            val itemName = editTextItemName.text.toString()
            val itemPrice = editTextItemPrice.text.toString()
            val itemURL = editTextItemURL.text.toString()

            // Create a new WishlistItem
            val newItem = WishlistItem(itemName, itemPrice, itemURL)

            // Add the item to your list (data source)
            wishlistItems.add(newItem)

            // Notify the adapter that data has changed
            wishlistAdapter.notifyDataSetChanged()

            // Clear input fields
            editTextItemName.text.clear()
            editTextItemPrice.text.clear()
            editTextItemURL.text.clear()
        }
    }
}
