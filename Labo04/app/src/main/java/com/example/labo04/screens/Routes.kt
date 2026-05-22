package com.example.labo04.screens

sealed class Routes(val route: String) {
    object Inicio : Routes("inicio")
    object Home : Routes("home")
    object AddTask : Routes("add_task")
}