package com.example.recipes.domain.usecase

import com.example.recipes.data.api.RetrofitClient
import com.example.recipes.data.repository.RecipesListRespositoryImpl
import com.example.recipes.domain.model.RecipesList
import kotlinx.coroutines.flow.Flow

class GetRecipesListUseCase() {

    private val recipesListRespository = RecipesListRespositoryImpl()

    operator fun invoke(query: String): Flow<List<RecipesList.Recipes>>{

        return recipesListRespository.getRecipesList(query)
    }
}