package com.example.labo02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PantallaListadoNombres()
        }
    }
}
@Composable
fun PantallaListadoNombres() {
    var nameInput by remember { mutableStateOf("") }
    val nameList = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = nameInput,
            onValueChange = {nameInput = it},
            label = {Text("Nombre")},
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (nameInput.isNotBlank()) {
                    nameList.add(nameInput)
                    nameInput = ""
                }
            },
            modifier = Modifier.padding(vertical = 15.dp)
        ) {
            Text("Guardar")
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Listado de nombres y\nposición en la lista")

            Button(onClick = { nameList.clear() }) {
                Text("Limpiar")
            }
        }
        Spacer(modifier = Modifier.height(15.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .border(2.dp, Color.Blue, RoundedCornerShape(10.dp))
                .padding(15.dp)
        ) {
            itemsIndexed(nameList) { indice, item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = item)
                    Text(text = "${indice + 1}")
                }
            }
        }
    }
}