package com.example.recipes.data.datasource.recipelist

import com.example.recipes.data.model.response.SupermarketProductListResponse
import kotlinx.coroutines.flow.Flow

interface SupermarketProductListDataSource {
    fun getSupermarketProductList(query: String, offset: Int): Flow<SupermarketProductListResponse>
}