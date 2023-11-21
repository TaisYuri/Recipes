package com.example.recipes.presentation.supermarketproducts

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipes.domain.model.SupermarketProductList
import com.example.recipes.domain.usecase.GetSupermarketProductLisUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SupermarketProductsViewModel @Inject constructor(
    private val supermarketProductsUseCase: GetSupermarketProductLisUseCase
): ViewModel() {

    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

    val supermarketProducts = MutableLiveData<List<SupermarketProductList.SupermarketProduct>>()
    val error = MutableLiveData<Boolean>()

    init{
        getSupermarketList()
    }


    fun getSupermarketList(){
        viewModelScope.launch {
            supermarketProductsUseCase.invoke("vegan", 1)
                .flowOn(dispatcher)
                .catch { error.value = true
                    it.message?.let { it1 -> Log.d("ERROR", it1) }
                }
                .collectLatest {
                    supermarketProducts.value = it
                }
        }
    }
}