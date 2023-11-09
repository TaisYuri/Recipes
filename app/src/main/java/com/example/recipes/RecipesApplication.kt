package com.example.recipes

import android.app.Application
import com.example.recipes.di.dataModule
import com.example.recipes.di.domainModule
import com.example.recipes.di.networkModule
import com.example.recipes.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RecipesApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@RecipesApplication)
            modules(
                listOf(
                    networkModule,
                    domainModule,
                    dataModule,
                    presentationModule,
                )
            )
        }
    }
}