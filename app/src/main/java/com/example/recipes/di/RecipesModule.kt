package com.example.recipes.di

import com.example.recipes.data.api.Service
import com.example.recipes.data.datasource.recipelist.RecipeListDataSource
import com.example.recipes.data.datasource.recipelist.RecipeListDataSourceImpl
import com.example.recipes.data.repository.RecipesListRespositoryImpl
import com.example.recipes.domain.repository.RecipeListRepository
import com.example.recipes.domain.usecase.GetRecipesListUseCase
import com.example.recipes.presentation.recipes.RecipesListViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single<Gson> { GsonBuilder().create() }
    single {
        OkHttpClient.Builder()
            .build()
    }
    single<GsonConverterFactory> { GsonConverterFactory.create(get()) }

    single<Retrofit.Builder> {
        Retrofit.Builder()
            .client(get())
            .addConverterFactory(get<GsonConverterFactory>())
    }
    single<Retrofit>(named("recipes")) {
        get<Retrofit.Builder>()
            .baseUrl("https://api.spoonacular.com/")
            .build()
    }
    single<Service> {
        get<Retrofit>(named("recipes")).create(Service::class.java)
    }
}



val domainModule = module {
    factory { GetRecipesListUseCase(get()) }
}

val dataModule = module {
    factory<RecipeListDataSource> { RecipeListDataSourceImpl(get()) }
    factory<RecipeListRepository> { RecipesListRespositoryImpl(get()) }
}

val presentationModule = module {
    viewModel {
        RecipesListViewModel(
            recipesListUseCase = get()
        )
    }
}