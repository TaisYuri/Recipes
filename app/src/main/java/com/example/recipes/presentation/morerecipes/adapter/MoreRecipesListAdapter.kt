package com.example.recipes.presentation.morerecipes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipes.databinding.ItemMoreItemsRecyclerBinding
import com.example.recipes.domain.model.RecipesList
import com.example.recipes.presentation.morerecipes.adapter.viewholder.MoreRecipesListViewHolder

class MoreRecipesListAdapter: RecyclerView.Adapter<MoreRecipesListViewHolder>() {

    //private var values: List<RecipesList.Recipes> = emptyList()
    private var values: List<Int> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoreRecipesListViewHolder {
        return MoreRecipesListViewHolder(ItemMoreItemsRecyclerBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun getItemCount(): Int {
       return values.size
    }

    override fun onBindViewHolder(holder: MoreRecipesListViewHolder, position: Int) {
        holder.bindItem(values[position])
    }

    /*fun updateList(list: List<RecipesList.Recipes>){
        values = list
        notifyDataSetChanged()
    } */

    fun updateList2(list: List<Int>){
        values = list
        notifyDataSetChanged()
    }

}