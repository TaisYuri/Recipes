package com.example.recipes.di

import com.example.recipes.data.api.Service
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object NetworkModules {


    fun getRetrofitInstance(): Retrofit {
        val httpClient = OkHttpClient.Builder()

        return Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //HILT NÃO ACEITA GENERICS
    /*   fun <T> getService(serviceClass: Class<T>): T {
           return getRetrofitInstance().create(serviceClass)
       }*/

    fun getService(retrofit: Retrofit): Service {
        return retrofit.create(Service::class.java)
    }
}