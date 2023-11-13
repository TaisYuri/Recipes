package com.example.recipes.stubs

import com.example.recipes.data.model.response.RecipesDataResponse
import com.example.recipes.data.model.response.RecipesListResponse
import com.example.recipes.domain.model.RecipesList
import kotlinx.coroutines.flow.flow

val stubData = RecipesList.Recipes(
    id = 664664,
    title = "Vegetarian Haggis",
    image = "https://spoonacular.com/recipeImages/664664-312x231.jpg",
    readyInMinutes = 45
)


val stubDataResponse = RecipesDataResponse(
    id = 664664,
    title = "Vegetarian Haggis",
    image = "https://spoonacular.com/recipeImages/664664-312x231.jpg",
    readyInMinutes = 45
)


val stubListResponse = RecipesListResponse(
    results = listOf(stubDataResponse)
)