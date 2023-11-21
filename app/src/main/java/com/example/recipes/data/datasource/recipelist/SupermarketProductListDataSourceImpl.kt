package com.example.recipes.data.datasource.recipelist

import com.example.recipes.data.api.Service
import com.example.recipes.data.model.response.SupermarketProductListResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SupermarketProductListDataSourceImpl @Inject constructor(private val service: Service) :
    SupermarketProductListDataSource {
    override fun getSupermarketProductList(
        query: String,
        offset: Int
    ): Flow<SupermarketProductListResponse> =
        flow {
            emit(
                service.getSupermarketProductList(
                    query,
                    "dc84ea2d78c84ebe979d198be7cbd160",
                    offset
                )
            )
        }


}