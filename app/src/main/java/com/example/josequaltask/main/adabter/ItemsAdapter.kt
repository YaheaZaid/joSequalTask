package com.example.josequaltask.main.adabter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.josequaltask.R
import com.example.josequaltask.main.model.MapItem
import com.google.android.gms.maps.model.LatLng

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
}
class ItemsAdapter(private val itemList: List<MapItem>, private val onItemClick: (LatLng) -> Unit) :
    RecyclerView.Adapter<ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = itemList[position]

        holder.titleTextView.text = currentItem.title

        holder.itemView.setOnClickListener {
            onItemClick.invoke(currentItem.position)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}