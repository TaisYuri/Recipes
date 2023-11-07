package com.example.recipes.data.datasource.recipelist

import com.example.recipes.data.api.Service
import com.example.recipes.data.model.response.RecipesListResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RecipeListDataSourceImpl @Inject constructor(private val service: Service ):RecipeListDataSource {

    //private val service = RetrofitClient.getService(Service::class.java)
    override fun getRecipesList(query: String): Flow<RecipesListResponse> =
        flow { emit(service.getRecipesList(query,"dc84ea2d78c84ebe979d198be7cbd160", true, 50 )) }
}