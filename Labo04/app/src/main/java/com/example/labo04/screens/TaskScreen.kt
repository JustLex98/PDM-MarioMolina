package com.example.labo04.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.labo04.model.Task
import com.example.labo04.viewmodel.GeneralViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(navController: NavHostController, viewModel: GeneralViewModel) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val tasks = viewModel.tasks.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nueva Tarea") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Regresar"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Título") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    val newTask = Task(
                        id = tasks.value.size + 1,
                        title = title,
                        description = description
                    )
                    viewModel.addTask(newTask)
                    navController.popBackStack()
                },
                enabled = title.isNotBlank()
            ) {
                Text("Crear Tarea")
            }

            TextButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = { navController.popBackStack() }
            ) {
                Text("Cancelar y Volver")
            }
        }
    }
}
