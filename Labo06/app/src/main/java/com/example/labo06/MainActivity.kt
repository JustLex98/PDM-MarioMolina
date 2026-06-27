package com.example.labo06

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.labo06.ui.screens.RecipeScreen
import com.example.labo06.ui.theme.Labo06Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Labo06Theme {
                RecipeScreen()
            }
        }
    }
}