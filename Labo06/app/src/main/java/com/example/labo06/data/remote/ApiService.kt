package com.example.labo06.data.remote

import com.example.labo06.data.model.MealResponse
import retrofit2.http.GET

interface ApiService {
    @GET("search.php?s=")
    suspend fun getMeals(): MealResponse
}