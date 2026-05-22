package com.example.labo04.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.labo04.model.Task
import com.example.labo04.viewmodel.GeneralViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController, viewModel: GeneralViewModel) {
    val tasks = viewModel.tasks.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mis Tareas") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Routes.AddTask.route) }) {
                Icon(Icons.Default.Add, contentDescription = "Agregar")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            items(tasks.value) { task ->
                TaskCard(task)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun TaskCard(task: Task) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Título: ${task.title}",
                style = MaterialTheme.typography.titleMedium
            )
            if (task.description.isNotBlank()) {
                Text(
                    text = "Descripción: ${task.description}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Text(
                text = "Fecha: ${task.endDate}",
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}