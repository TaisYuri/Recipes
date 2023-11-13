package com.example.recipes.data.repository

import app.cash.turbine.test
import com.example.recipes.data.datasource.recipelist.RecipeListDataSourceImpl
import com.example.recipes.stubs.stubData
import com.example.recipes.stubs.stubListResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import kotlin.test.assertFailsWith

class RecipesListRespositoryImplTest {
    private val datasource = mockk<RecipeListDataSourceImpl>()
    private val repository = RecipesListRespositoryImpl(datasource)

    @Test
    fun `getRecipesList should return response when service has success`() {
        runBlocking {

            // Given
            val expectedRecipes = stubData
            coEvery { datasource.getRecipesList("pasta") } returns flowOf(stubListResponse)

            // When
            val response = repository.getRecipesList("pasta")
                .map { repository.mapRecipesListItem(stubListResponse) }

            // Then
            response.test {
                assertEquals(listOf(expectedRecipes), this.expectMostRecentItem())
            }
        }
    }

}