package com.example.recipes.domain.usecase

import com.example.recipes.data.repository.RecipesListRespositoryImpl
import com.example.recipes.stubs.stubData
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Test
import kotlin.test.assertFailsWith

class GetRecipesListUseCaseTest {

    private val repository = mockk<RecipesListRespositoryImpl>()
    private val useCase = GetRecipesListUseCase(repository)

    @Test
    fun `invoke should return Recipeslist when repository returns success`(){
        runBlocking {

            // Given
            val expectedRecipes = listOf(stubData)
            coEvery{ repository.getRecipesList("pasta")} returns flowOf(expectedRecipes)

            // When
            val response = useCase.invoke("pasta")

            // Then
            assertEquals(expectedRecipes, response.last())

        }
    }
    @Test
    fun `invoke should throw error when repository returns throwable`(){
        runBlocking {
            // Given
            coEvery { repository.getRecipesList("pasta") } returns flow{ throw Throwable() }

            // When
            val response = useCase.invoke("pasta")

            // Then
            assertFailsWith<Throwable> {
                response.collect()
            }
        }
    }
}