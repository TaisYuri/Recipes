package com.example.recipes.domain.repository

import com.example.recipes.domain.model.SupermarketProductList
import kotlinx.coroutines.flow.Flow

interface SupermarketProductListRepository {
    fun getSupermarketProductList(query: String, offset: Int): Flow<List<SupermarketProductList.SupermarketProduct>>
}