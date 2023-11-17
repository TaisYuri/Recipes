package com.example.recipes.presentation.recipes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.recipes.domain.model.RecipesList
import com.example.recipes.domain.usecase.GetRecipesListUseCase
import com.example.recipes.stubs.stubData
import com.google.common.truth.Truth
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyOrder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class RecipesListViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    private val mainCoroutineRule = MainCoroutineRule(UnconfinedTestDispatcher())

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val useCase: GetRecipesListUseCase = mockk(relaxed = true)
    private var recipesListObserver: Observer<List<RecipesList.Recipes>> = mockk(relaxed = true)
    private var mutable: MutableLiveData<List<RecipesList.Recipes>> = mockk(relaxed = true)
    private lateinit var recipesViewModel: RecipesListViewModel

    @BeforeEach
    open fun beforeEach() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        recipesViewModel.recipesList.observeForever(recipesListObserver)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getRecipesList should return RecipesList`() = runTest{
        // Given
        val expectedRecipes = listOf(stubData)
        coEvery { useCase.invoke("pasta") } returns flow { emit(expectedRecipes) }

        // When
        setupViewModel()

        // Then
        verify {
            recipesListObserver.onChanged(expectedRecipes)
        }
        verifyOrder {
            recipesListObserver.onChanged(emptyList())
            recipesListObserver.onChanged(expectedRecipes)
        }

    }

    fun setupViewModel() {
        recipesViewModel = RecipesListViewModel(recipesListUseCase = useCase)
    }
}

