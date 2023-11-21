package com.example.recipes.presentation.morerecipes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipes.domain.model.RecipesList
import com.example.recipes.domain.usecase.GetRecipesListUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class MoreRecipesViewModel(private val useCase: GetRecipesListUseCase): ViewModel() {
    private var NUMBER = 15

    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

    val recipesList = MutableLiveData<List<RecipesList.Recipes>>()

    init{
        getRecipesMore(1)
    }

    fun getRecipesMore(offset: Int){
        viewModelScope.launch {
            useCase.invoke("pasta", NUMBER, offset)
                .flowOn(dispatcher)
                .collectLatest {
                    recipesList.value = it
                }
        }
    }

    fun onLoadOffset(offset: Int){
        getRecipesMore(offset)
    }

}