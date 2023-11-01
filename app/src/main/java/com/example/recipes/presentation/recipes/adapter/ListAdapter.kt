package com.example.recipes.presentation.recipes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipes.databinding.ItemRecyclerBinding
import com.example.recipes.domain.model.RecipesList
import com.example.recipes.presentation.recipes.adapter.viewholder.ListItemViewHolder

interface ItemListener{
    fun onItemSelected(position: Int)
}

class ListAdapter(private val listener: ItemListener):RecyclerView.Adapter<ListItemViewHolder>() {

    private var values: List<RecipesList.Recipes> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        return ListItemViewHolder(ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return values.size
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        val item = values[position]

        holder.bindItem(item)
    }

    fun updateList(list: List<RecipesList.Recipes>){
        values = list
        notifyDataSetChanged()
    }


}