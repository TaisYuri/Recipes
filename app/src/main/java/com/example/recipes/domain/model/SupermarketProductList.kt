package com.example.recipes.domain.model

data class SupermarketProductList (
    val supermarketProduct: List<SupermarketProduct>
){
    data class SupermarketProduct(
        val id: Int,
        val name: String,
        val image: String
    )
}