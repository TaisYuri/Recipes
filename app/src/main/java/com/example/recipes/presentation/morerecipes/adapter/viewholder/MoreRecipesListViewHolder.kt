package com.example.recipes.presentation.morerecipes.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.recipes.databinding.ItemMoreItemsRecyclerBinding
import com.example.recipes.domain.model.RecipesList

class MoreRecipesListViewHolder(private val binding: ItemMoreItemsRecyclerBinding): RecyclerView.ViewHolder(binding.root) {

    fun bindItem(item: RecipesList.Recipes){
        binding.apply {
            this.textTitle.text = item.title
        }
    }
}