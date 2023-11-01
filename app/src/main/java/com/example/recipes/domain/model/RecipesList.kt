package com.example.recipes.domain.model


data class RecipesList (
    val recipes: List<Recipes>
){
    data class Recipes(
        val id: Int,
        val title: String,
        val image: String,
        val readyInMinutes: Int,
    )
}