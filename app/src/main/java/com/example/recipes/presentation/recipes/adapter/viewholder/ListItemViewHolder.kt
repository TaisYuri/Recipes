package com.example.recipes.presentation.recipes.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.recipes.databinding.ItemRecyclerBinding
import com.example.recipes.domain.model.RecipesList
import com.example.recipes.presentation.viewbinder.bindSrcUrl

class ListItemViewHolder(private val binding: ItemRecyclerBinding): RecyclerView.ViewHolder(binding.root) {

    fun bindItem(item: RecipesList.Recipes){
        binding.textId.text = "id: ${item.id.toString()}"
        binding.textTitle.text = item.title
        binding.imageView.bindSrcUrl(item.image)
        binding.textTime.text = item.readyInMinutes.toString()
    }
}