package com.example.recipes.presentation.recipes

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipes.domain.model.RecipesList
import com.example.recipes.domain.usecase.GetRecipesListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesListViewModel @Inject constructor(
    private val recipesListUseCase: GetRecipesListUseCase,
): ViewModel() {

    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

    //private val recipesListUseCase = GetRecipesListUseCase()

    val recipesList = MutableLiveData<List<RecipesList.Recipes>>()
    val recipesList2 = MutableLiveData<List<RecipesList.Recipes>>()
    var isLoading = MutableLiveData<Boolean>()
    var isError = MutableLiveData<Boolean>()

    init{
        getRecipesList(recipesList, "pizza")
        getRecipesList(recipesList2,"vegetarian")
    }

    fun getRecipesList(list: MutableLiveData<List<RecipesList.Recipes>>, typeRecipes: String){
        viewModelScope.launch {
            recipesListUseCase.invoke(typeRecipes)
                .flowOn(dispatcher)
                .onStart {  isLoading.value = true }
                .catch {
                    isLoading.value = false
                    isError.value = true
                    handleError(it)
                }
                .collectLatest {
                    isLoading.value = false
                    isError.value = false
                    list.value = it
                }
        }
    }

    fun onSelected(item: String){
        getRecipesList(recipesList2, item)
    }

    private fun handleError(throwable: Throwable) {
        Log.i("ERRO: ", throwable.localizedMessage)
    }
}