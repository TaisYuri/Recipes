package com.example.recipes.data.repository

import app.cash.turbine.test
import com.example.recipes.data.datasource.recipelist.RecipeListDataSourceImpl
import com.example.recipes.stubs.stubData
import com.example.recipes.stubs.stubListResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class RecipesListRespositoryImplTest {
    private val datasource = mockk<RecipeListDataSourceImpl>()
    private val repository = RecipesListRespositoryImpl(datasource)

    @Test
    fun `getRecipesList should return response when service has success`() {
        runBlocking {

            // Given
            val expectedRecipes = stubData
            coEvery { datasource.getRecipesList("pasta", 10,1) } returns flowOf(stubListResponse)

            // When
            val response = repository.getRecipesList("pasta",10,1)


            // Then
            response.test {
                assertEquals(listOf(expectedRecipes), this.expectMostRecentItem())
            }
        }
    }

}