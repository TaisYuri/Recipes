package com.example.recipes.data.datasource.recipelist

import com.example.recipes.data.api.RetrofitClient
import com.example.recipes.data.api.Service
import com.example.recipes.data.model.response.RecipeDetailResponse
import com.example.recipes.data.model.response.RecipesListResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RecipeListDataSourceImpl:RecipeListDataSource {

    private val service = RetrofitClient.getService(Service::class.java)

    override fun getRecipesList(query: String): Flow<RecipesListResponse> =
        flow { emit(service.getRecipesList(query,"dc84ea2d78c84ebe979d198be7cbd160", true, 50 )) }
}