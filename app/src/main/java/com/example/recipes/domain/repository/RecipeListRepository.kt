package com.example.recipes.domain.repository

import com.example.recipes.domain.model.RecipesList
import kotlinx.coroutines.flow.Flow

interface RecipeListRepository {

    fun getRecipesList(query: String, number: Int, offset: Int): Flow<List<RecipesList.Recipes>>
}