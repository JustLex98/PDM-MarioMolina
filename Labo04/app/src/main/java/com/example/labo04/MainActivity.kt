package com.example.labo04

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.labo04.screens.AddTaskScreen
import com.example.labo04.screens.HomeScreen
import com.example.labo04.screens.InicioScreen
import com.example.labo04.screens.Routes
import com.example.labo04.ui.theme.Labo04Theme
import com.example.labo04.viewmodel.GeneralViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Labo04Theme {
                val navController = rememberNavController()
                val viewModel: GeneralViewModel = viewModel()

                Surface(modifier = Modifier.fillMaxSize()) {
                    NavHost(
                        navController = navController,
                        startDestination = Routes.Inicio.route
                    ) {
                        composable(Routes.Inicio.route) {
                            InicioScreen(navController)
                        }
                        composable(Routes.Home.route) {
                            HomeScreen(navController, viewModel)
                        }
                        composable(Routes.AddTask.route) {
                            AddTaskScreen(navController, viewModel)
                        }
                    }
                }
            }
        }
    }
}