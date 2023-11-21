package com.example.recipes.data.api

import com.example.recipes.data.model.response.RecipesListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("recipes/complexSearch")
    suspend fun getRecipesList(
        @Query("query") query: String,
        @Query("apiKey") apiKey: String,
        @Query("addRecipeInformation") addRecipeInformation: Boolean,
        @Query("number") number: Int,
        @Query("offset") offset: Int,
    ): RecipesListResponse

}