package com.example.recipes.data.repository

import com.example.recipes.data.datasource.recipelist.RecipeListDataSource
import com.example.recipes.data.model.response.RecipesListResponse
import com.example.recipes.domain.model.RecipesList
import com.example.recipes.domain.repository.RecipeListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RecipesListRespositoryImpl (private val recipesDataSource: RecipeListDataSource) :
    RecipeListRepository {

    //private val recipesDataSource = RecipeListDataSourceImpl()
    override fun getRecipesList(query: String): Flow<List<RecipesList.Recipes>> =
        recipesDataSource.getRecipesList(query).map {
            mapRecipesListItem(it)
        }

    private fun mapRecipesListItem(recipesItem: RecipesListResponse) =
        recipesItem.results.map { itemResponse ->
            RecipesList.Recipes(
                id = itemResponse.id,
                title = itemResponse.title,
                image = itemResponse.image,
                readyInMinutes = itemResponse.readyInMinutes
            )
        }
}