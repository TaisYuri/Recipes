package com.example.recipes

import app.cash.turbine.test
import com.example.recipes.data.datasource.recipelist.RecipeListDataSourceImpl
import com.example.recipes.data.repository.RecipesListRespositoryImpl
import com.example.recipes.domain.model.RecipesList
import io.mockk.coEvery
import io.mockk.every
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime
class RecipesListRepositoryTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    private val recipesDataSource: RecipeListDataSourceImpl = mockk()
    private val repository = RecipesListRespositoryImpl(recipesDataSource)

    @Before
    fun setup() {
        every { recipesDataSource.getRecipesList("pasta")} returns flowOf()
    }

    @Test
    fun getData_whenApiSourceHadSucess_shouldResultList() = runBlocking{

        //given
        val expected = getStubRecipesList

        //when
        val response = repository.getRecipesList("pasta")

        //then
     /*   response.test{
            assertEquals(expected, expectItem())
        }*/
    }
}

internal val getStubRecipe =
    RecipesList.Recipes(
        id = 664664,
        title = "Vegetarian Haggis",
        image = "https://spoonacular.com/recipeImages/664664-312x231.jpg",
        readyInMinutes = 45
    )

internal val getStubRecipesList =
    RecipesList(
        recipes = listOf(getStubRecipe)
    )

