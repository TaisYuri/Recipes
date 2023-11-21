package com.example.recipes.data.datasource.recipelist

import com.example.recipes.data.model.response.RecipesListResponse
import kotlinx.coroutines.flow.Flow

interface RecipeListDataSource {

    fun getRecipesList(query: String, number: Int, offset: Int): Flow<RecipesListResponse>

}