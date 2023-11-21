package com.example.recipes.data.repository

import com.example.recipes.data.datasource.recipelist.SupermarketProductListDataSourceImpl
import com.example.recipes.data.model.response.SupermarketProductListResponse
import com.example.recipes.domain.model.SupermarketProductList
import com.example.recipes.domain.repository.SupermarketProductListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SupermarketProductListRepositoryImpl @Inject constructor(
    private val supermarketProductListDataSource: SupermarketProductListDataSourceImpl
) : SupermarketProductListRepository {

    override fun getSupermarketProductList(
        query: String,
        offset: Int
    ): Flow<List<SupermarketProductList.SupermarketProduct>> =
        supermarketProductListDataSource.getSupermarketProductList(query, offset).map {
            mapSupermarketProductListItem(it)

        }

    private fun mapSupermarketProductListItem(item: SupermarketProductListResponse) =
        item.results.map { itemResponse ->
            SupermarketProductList.SupermarketProduct(
                id = itemResponse.id,
                name = itemResponse.name,
                image = itemResponse.image
            )
        }


}

