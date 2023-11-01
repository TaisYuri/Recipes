package com.example.recipes.data.mapper

import com.example.recipes.data.model.response.RecipeDetailResponse
import com.example.recipes.data.model.response.RecipesListResponse
import com.example.recipes.domain.model.RecipesList

interface RecipesMapper {

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