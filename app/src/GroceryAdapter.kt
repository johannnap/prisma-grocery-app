package com.prisma.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.prisma.app.R

class GroceryAdapter(
    private val items: MutableList<GroceryItem>,
    private val onRemoveItem: (Int) -> Unit
) : RecyclerView.Adapter<GroceryAdapter.GroceryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_grocery, parent, false)
        return GroceryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class GroceryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val checkBox: CheckBox = itemView.findViewById(R.id.checkbox)
        private val quantityView: TextView = itemView.findViewById(R.id.quantityView)
        private val plusButton: ImageButton = itemView.findViewById(R.id.plusButton)
        private val minusButton: ImageButton = itemView.findViewById(R.id.minusButton)

        fun bind(item: GroceryItem) {
            checkBox.text = item.name
            checkBox.isChecked = item.isSelected
            quantityView.text = item.quantity.toString()

            checkBox.setOnCheckedChangeListener { _, isChecked ->
                item.isSelected = isChecked
            }

            plusButton.setOnClickListener {
                item.quantity++
                quantityView.text = item.quantity.toString()
            }

            minusButton.setOnClickListener {
                if (item.quantity > 1) {
                    item.quantity--
                    quantityView.text = item.quantity.toString()
                }
            }

            itemView.setOnLongClickListener {
                onRemoveItem(adapterPosition)
                true
            }
        }
    }
}
