package com.example.recipes.presentation.morerecipes

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipes.domain.model.RecipesList
import com.example.recipes.domain.usecase.GetRecipesListUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MoreRecipesViewModel(private val useCase: GetRecipesListUseCase): ViewModel() {
    private var NUMBER = 15
    private var lastPage = 10
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

    val recipesList = MutableLiveData<List<RecipesList.Recipes>>()
    var isLoading = MutableLiveData<Boolean>()
    private var offset :Int = 1


    val lista: MutableList<Int> = (0..30).toMutableList()
    val showList = MutableLiveData<List<Int>>()


    init{
        getNumbers()
        //getRecipesMore(offset)
    }

    fun getRecipesMore(offset: Int){
        viewModelScope.launch {
            useCase.invoke2("pasta", NUMBER, offset)
                .flowOn(dispatcher)
                .onStart {  isLoading.value = true }
                .collectLatest {
                    isLoading.value = false
                    recipesList.value = it
                }
        }
    }
    fun getNumbers(){
         showList.value = lista
    }

    fun loadMoreItems(){
        offset += 1
        //getRecipesMore(offset)

        val startIndex = lista.size
        val endIndex = startIndex + 20

        isLoading.value = true
        android.os.Handler().postDelayed({
            Log.d("CHecando", offset.toString())
            isLoading.value = false
            lista.addAll(startIndex until endIndex)
            showList.value = lista
        }, 5000);


    }

    fun isLoading() = isLoading.value ?: false
    fun isLastPage() = offset == lastPage




}