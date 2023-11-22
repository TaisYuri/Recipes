
import android.os.Looper
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.recipes.domain.model.RecipesList
import com.example.recipes.domain.usecase.GetRecipesListUseCase
import com.example.recipes.presentation.recipes.RecipesListViewModel
import com.example.recipes.stubs.stubData
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Rule
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.reset
import org.mockito.kotlin.verify

@OptIn(ExperimentalCoroutinesApi::class)
class RecipesListViewModelTest {

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    private val testDispatcher = UnconfinedTestDispatcher()

    private val useCase: GetRecipesListUseCase = mockk(relaxed = true)
    private var recipesListObserver: Observer<List<RecipesList.Recipes>> = mock()
    private lateinit var recipesViewModel: RecipesListViewModel

    @BeforeEach
    fun beforeEach() {
        Dispatchers.setMain(testDispatcher)
        mockLooper()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Test
    fun `getRecipesList should return RecipesList`() = runTest {
        // Given
        reset(recipesListObserver)
        val expectedRecipes = listOf(stubData)
        coEvery { useCase.invoke2("pizza", 50, 1) } returns flow { emit(expectedRecipes) }

        // When
        recipesViewModel = RecipesListViewModel(recipesListUseCase = useCase, dispatcher = testDispatcher)
        recipesViewModel.recipesList.observeForever(recipesListObserver)

        // Then
        verify(recipesListObserver).onChanged(expectedRecipes)

    }


    private fun mockLooper() {
        mockkStatic(Looper::class)
        val looper = mockk<Looper> {
            every { thread } returns Thread.currentThread()
        }
        every { Looper.getMainLooper() } returns looper
    }
}