package com.example.recipes.data.datasource.recipelist

import app.cash.turbine.test
import com.example.recipes.data.api.Service
import com.example.recipes.stubs.stubListResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class RecipeListDataSourceImplTest{

    private val service = mockk<Service>()
    private val datasource = RecipeListDataSourceImpl(service)

    @Test
    fun `getRecipesList should return response when service has success`(){
        runBlocking {

            // Given
            val expectedRecipes = stubListResponse
            coEvery { service.getRecipesList("pasta","dc84ea2d78c84ebe979d198be7cbd160", true, 50) } returns expectedRecipes

            // When
            val response = datasource.getRecipesList("pasta")

            // Then
            val result = response.toList()
            assertEquals(listOf( expectedRecipes), result)
        }
    }

    @Test
    fun `getRecipesList should throw a error`(){
        runBlocking {
            // Given
            coEvery { service.getRecipesList("pasta","", true, 50) } throws Exception("")

            // When
            val response = datasource.getRecipesList("pasta")

            // Then
           assertFailsWith<RuntimeException> {
               response.collect()
           }
        }
    }
}
