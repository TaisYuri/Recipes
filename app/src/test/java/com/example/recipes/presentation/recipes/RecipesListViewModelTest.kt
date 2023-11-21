package com.example.recipes.presentation.recipes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.recipes.domain.model.RecipesList
import com.example.recipes.domain.usecase.GetRecipesListUseCase
import com.example.recipes.stubs.stubData
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RecipesListViewModelTest{

    private lateinit var useCase: GetRecipesListUseCase
    private lateinit var recipesViewModel: RecipesListViewModel
    private lateinit var recipesListObserver: Observer<List<RecipesList.Recipes>>
    private var mutable= MutableLiveData<List<RecipesList.Recipes>>()
    val expectedRecipes = listOf(stubData)

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @ObsoleteCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @ExperimentalCoroutinesApi
    @ObsoleteCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        useCase = mockk(relaxed = true)
        recipesViewModel = RecipesListViewModel(useCase)
        recipesListObserver = mockk(relaxed = true)

        runBlocking {
            coEvery { useCase.invoke("pasta") } returns flowOf (expectedRecipes)
        }
    }

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun `getRecipesList should return RecipesList`() = runBlocking{

        // Given
        recipesViewModel.recipesList.observeForever(recipesListObserver)
        recipesViewModel.getRecipesList(mutable,"pasta")
        delay(10)


        verify(exactly = 1) { recipesViewModel.getRecipesList(mutable,"pasta") }

        verify(timeout = 50) { recipesListObserver.onChanged(expectedRecipes) }
    }

    fun setupViewModel() {
        recipesViewModel = RecipesListViewModel(recipesListUseCase = useCase)
    }
}