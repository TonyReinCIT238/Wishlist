package com.example.wishlist2

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class WishlistAdapter(private val items: MutableList<WishlistItem>) :
    RecyclerView.Adapter<WishlistAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.textItemName)
        val itemPrice: TextView = itemView.findViewById(R.id.textItemPrice)
        val itemURL: TextView = itemView.findViewById(R.id.textItemURL)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.wishlist_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.itemName.text = item.name
        holder.itemPrice.text = item.price
        holder.itemURL.text = item.url

        // Set an OnClickListener to open the URL in a web browser
        holder.itemView.setOnClickListener {
            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(item.url))
                ContextCompat.startActivity(it.context, browserIntent, null)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(it.context, "Invalid URL for " + item.name, Toast.LENGTH_LONG).show()
            }
        }

        // Set a long-press listener for item deletion
        holder.itemView.setOnLongClickListener {
            removeItem(position)
            true // Indicates that the long-press event is consumed
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun removeItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }
}
