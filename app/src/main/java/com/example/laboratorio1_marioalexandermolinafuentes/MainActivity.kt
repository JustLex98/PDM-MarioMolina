package com.example.laboratorio1_marioalexandermolinafuentes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.laboratorio1_marioalexandermolinafuentes.ui.theme.Laboratorio1_MarioAlexanderMolinaFuentesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Laboratorio1_MarioAlexanderMolinaFuentesTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    PantallaLaboratorio()
                }
            }
        }
    }
}

@Composable
fun PantallaLaboratorio() {
    val scrollState = rememberScrollState()

    // --- ESTADOS EJERCICIO 1 ---
    var miPC by remember { mutableStateOf(Computadora(32, 512, "Windows 11")) }
    var estaEncendidaUI by remember { mutableStateOf(false) }
    var inRAM by remember { mutableStateOf("") }
    var inAlm by remember { mutableStateOf("") }
    var inSO by remember { mutableStateOf("") }
    var resPC by remember { mutableStateOf("PC en espera.") }

    // --- ESTADOS EJERCICIO 2 ---
    var n1 by remember { mutableStateOf("") }
    var n2 by remember { mutableStateOf("") }
    var inPrecio by remember { mutableStateOf("") }
    var resCalc by remember { mutableStateOf("Resultado: 0.0") }
    val calc by remember { mutableStateOf(Calculadora("Texas Instrument", 10, 150.0)) }

    // --- ESTADOS EJERCICIO 3 ---
    var resLista by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(top = 60.dp, start = 16.dp, end = 16.dp, bottom = 16.dp).verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Laboratorio 1 - PDM", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)

        // CARD EJERCICIO 1: COMPUTADORA
        Card(elevation = CardDefaults.cardElevation(4.dp)) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text("1. Computadora Interactiva", fontWeight = FontWeight.Bold)

                // Botón Encender/Apagar
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(if (estaEncendidaUI) "Estado: ON" else "Estado: OFF")
                    Switch(checked = estaEncendidaUI, onCheckedChange = {
                        estaEncendidaUI = it
                        resPC = if (it) miPC.encender() else miPC.apagar()
                    })
                }

                Text("Specs: ${miPC.Memoria_RAM}GB RAM | ${miPC.almacenamiento}GB Disco | ${miPC.sistemaOperativo}", fontSize = 11.sp, color = Color.Blue)

                OutlinedTextField(value = inRAM, onValueChange = { inRAM = it }, label = { Text("Nueva RAM") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), modifier = Modifier.fillMaxWidth())
                OutlinedTextField(value = inAlm, onValueChange = { inAlm = it }, label = { Text("Nuevo Almacenamiento") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), modifier = Modifier.fillMaxWidth())
                OutlinedTextField(value = inSO, onValueChange = { inSO = it }, label = { Text("Nuevo SO") }, modifier = Modifier.fillMaxWidth())

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(onClick = {
                        miPC.Memoria_RAM = inRAM.toIntOrNull() ?: miPC.Memoria_RAM
                        miPC.almacenamiento = inAlm.toIntOrNull() ?: miPC.almacenamiento
                        if (inSO.isNotEmpty()) miPC.sistemaOperativo = inSO
                        resPC = "Componentes actualizados."
                    }) { Text("Actualizar") }

                    Button(onClick = {
                        resPC = miPC.obtenerProgramas2026(listOf("Notion 2026", "Facebook 2024", "Spotify 2026"))
                    }) { Text("Filtro 2026") }
                }
                Text(resPC, fontSize = 12.sp, color = Color.Red)
            }
        }

        // CARD EJERCICIO 2: CALCULADORA
        Card(elevation = CardDefaults.cardElevation(4.dp)) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text("2. Calculadora", fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(value = n1, onValueChange = { n1 = it }, label = { Text("Num 1") }, modifier = Modifier.fillMaxWidth())
                OutlinedTextField(value = n2, onValueChange = { n2 = it }, label = { Text("Num 2") }, modifier = Modifier.fillMaxWidth())

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    Button(onClick = { resCalc = "Suma: ${calc.sumar(n1.toDoubleOrNull()?:0.0, n2.toDoubleOrNull()?:0.0)}" }) { Text("+") }
                    Button(onClick = { resCalc = "Resta: ${calc.restar(n1.toDoubleOrNull()?:0.0, n2.toDoubleOrNull()?:0.0)}" }) { Text("-") }
                    Button(onClick = { resCalc = "Multiplicar: ${calc.multiplicar(n1.toDoubleOrNull()?:0.0, n2.toDoubleOrNull()?:0.0)}" }) { Text("*") }
                    Button(onClick = { resCalc = "Div: ${calc.dividir(n1.toDoubleOrNull()?:0.0, n2.toDoubleOrNull()?:0.0)}" }) { Text("/") }
                }
                Text(resCalc, fontWeight = FontWeight.Bold, color = Color(0xFF006400))
            }
        }

        // CARD EJERCICIO 3: LISTA
        Card(elevation = CardDefaults.cardElevation(4.dp)) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text("3. Lista de Estudiantes PDM", fontWeight = FontWeight.Bold)
                Button(onClick = { resLista = obtenerListaPDM() }, modifier = Modifier.fillMaxWidth()) { Text("Filtrar Estudiantes") }
                if (resLista.isNotEmpty()) Text(resLista, fontSize = 13.sp)
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
    }
}