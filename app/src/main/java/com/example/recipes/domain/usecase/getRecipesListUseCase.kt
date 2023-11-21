package com.example.recipes.domain.usecase

import com.example.recipes.domain.model.RecipesList
import com.example.recipes.domain.repository.RecipeListRepository
import kotlinx.coroutines.flow.Flow

class GetRecipesListUseCase (private val recipesListRespository: RecipeListRepository) {

    //private val recipesListRespository = RecipesListRespositoryImpl()

    operator fun invoke(query: String,number: Int, offset: Int): Flow<List<RecipesList.Recipes>>{

        return recipesListRespository.getRecipesList(query, number, offset)
    }
}