package com.example.labo06.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.labo06.data.model.Meal
import com.example.labo06.data.remote.RetrofitInstance
import kotlinx.coroutines.launch
import java.io.IOException

class RecipeViewModel : ViewModel() {

    var meals by mutableStateOf<List<Meal>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun loadRecipes() {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                val response = RetrofitInstance.api.getMeals()
                meals = response.meals ?: emptyList()
            } catch (e: IOException) {
                errorMessage = "Sin conexión a Internet"
            } catch (e: Exception) {
                errorMessage = "Error al cargar datos"
            } finally {
                isLoading = false
            }
        }
    }
}