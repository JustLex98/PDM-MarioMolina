package com.example.labo06.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.labo06.data.model.Meal
import com.example.labo06.viewmodel.RecipeViewModel

@Composable
fun RecipeScreen(viewModel: RecipeViewModel = viewModel()) {

    LaunchedEffect(Unit) {
        viewModel.loadRecipes()
    }

    Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        if (viewModel.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (viewModel.errorMessage != null) {
            Text(text = viewModel.errorMessage!!, modifier = Modifier.align(Alignment.Center))
        } else {
            LazyColumn {
                items(viewModel.meals) { meal ->
                    RecipeItem(meal)
                }
            }
        }
    }
}

@Composable
fun RecipeItem(meal: Meal) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column {
            AsyncImage(
                model = meal.strMealThumb,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().height(180.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = meal.strMeal, style = MaterialTheme.typography.titleLarge)
                Text(text = "Category: ${meal.strCategory}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Area: ${meal.strArea}", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}