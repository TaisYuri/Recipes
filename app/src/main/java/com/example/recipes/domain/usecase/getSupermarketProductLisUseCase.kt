package com.example.recipes.domain.usecase

import com.example.recipes.data.repository.SupermarketProductListRepositoryImpl
import com.example.recipes.domain.model.SupermarketProductList
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSupermarketProductLisUseCase @Inject constructor(
    private val supermarketProductLisRepository: SupermarketProductListRepositoryImpl
){

    operator fun invoke(query: String, offset: Int): Flow<List<SupermarketProductList.SupermarketProduct>>{
        return supermarketProductLisRepository.getSupermarketProductList(query, offset)
    }
}