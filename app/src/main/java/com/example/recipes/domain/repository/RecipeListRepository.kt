package com.example.recipes.domain.repository

import com.example.recipes.domain.model.RecipesList
import kotlinx.coroutines.flow.Flow

interface RecipeListRepository {

    fun getRecipesList(query: String): Flow<List<RecipesList.Recipes>>
}