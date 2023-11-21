package com.example.recipes.data.api

import com.example.recipes.data.model.response.RecipesListResponse
import com.example.recipes.data.model.response.SupermarketProductListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("recipes/complexSearch")
    suspend fun getRecipesList(
        @Query("query") query: String,
        @Query("apiKey") apiKey: String,
        @Query("addRecipeInformation") addRecipeInformation: Boolean,
        @Query("number") number: Int,
    ): RecipesListResponse

    @GET("food/products/search")
    suspend fun getSupermarketProductList(
        @Query("query") query: String,
        @Query("apiKey") apiKey: String,
        @Query("offset") offset: Int
    ): SupermarketProductListResponse

}