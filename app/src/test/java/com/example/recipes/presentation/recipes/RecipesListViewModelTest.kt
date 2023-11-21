package com.example.recipes.presentation.recipes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.recipes.domain.model.RecipesList
import com.example.recipes.domain.usecase.GetRecipesListUseCase
import com.example.recipes.stubs.stubData
import com.google.common.truth.Truth
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyOrder
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith


@ExperimentalCoroutinesApi
class RecipesListViewModelTest {


    @OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
    val mainCoroutineRule = newSingleThreadContext("UI thread")

    private val useCase: GetRecipesListUseCase = mockk(relaxed = true)
    private var recipesListObserver: Observer<List<RecipesList.Recipes>> = mockk(relaxed = true)
    private lateinit var recipesViewModel: RecipesListViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(mainCoroutineRule)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainCoroutineRule.close()
    }

    @Test
    fun `getRecipesList should return RecipesList`() = runTest {
        val mutable = mutableListOf<List<RecipesList.Recipes>>()
        val stateFlow = MutableStateFlow(CreationExtras.Empty)
        val expectedRecipes = listOf(stubData)
        val job = launch(Dispatchers.Main) {
            mutable.add(expectedRecipes)

        }

        job.cancel()



        verifyOrder {
            recipesListObserver.onChanged(emptyList())
            recipesListObserver.onChanged(expectedRecipes)
        }

    }

    fun setupViewModel() {
        recipesViewModel = RecipesListViewModel(recipesListUseCase = useCase)
    }
}

