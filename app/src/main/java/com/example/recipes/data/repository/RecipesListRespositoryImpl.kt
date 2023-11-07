package com.example.recipes.data.repository

import com.example.recipes.data.datasource.recipelist.RecipeListDataSource
import com.example.recipes.data.datasource.recipelist.RecipeListDataSourceImpl
import com.example.recipes.data.mapper.RecipesMapper
import com.example.recipes.data.model.response.RecipesListResponse
import com.example.recipes.domain.model.RecipesList
import com.example.recipes.domain.repository.RecipeListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecipesListRespositoryImpl @Inject constructor(private val recipesDataSource: RecipeListDataSourceImpl) :
    RecipeListRepository {

    //private val recipesDataSource = RecipeListDataSourceImpl()
    override fun getRecipesList(query: String): Flow<List<RecipesList.Recipes>> =
        recipesDataSource.getRecipesList(query).map {
            mapRecipesListItem(it)
        }

    fun mapRecipesListItem(recipesItem: RecipesListResponse) =
        recipesItem.results.map { itemResponse ->
            RecipesList.Recipes(
                id = itemResponse.id,
                title = itemResponse.title,
                image = itemResponse.image,
                readyInMinutes = itemResponse.readyInMinutes
            )
        }
}