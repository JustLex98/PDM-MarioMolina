package com.example.laboratorio1_marioalexandermolinafuentes

// --- Ejercicio 1: Computadora ---
class Computadora(var Memoria_RAM: Int, var almacenamiento: Int, var sistemaOperativo: String) {
    var estaEncendida: Boolean = false

    fun encender(): String {
        estaEncendida = true
        return "Sistema $sistemaOperativo iniciado. [ON]"
    }

    fun apagar(): String {
        estaEncendida = false
        return "Sistema apagado. [OFF]"
    }

    fun obtenerProgramas2026(programas: List<String>): String {
        if (!estaEncendida) return "Error: La computadora está apagada."
        val añoActual = "2026"
        val filtrados = programas.filter { it.contains(añoActual) }
        return "Programas 2026: ${filtrados.joinToString(", ")}"
    }
}

// --- Ejercicio 2: Calculadora ---
class Calculadora(
    val marca: String,
    val añosVida: Int,
    var precio: Double
) {
    fun sumar(a: Double, b: Double) = a + b
    fun restar(a: Double, b: Double) = a - b
    fun multiplicar(a: Double, b: Double) = a * b
    fun dividir(a: Double, b: Double) = if (b != 0.0) a / b else "Error: Div por 0"
}

// --- Ejercicio 3: Estudiante ---
data class Estudiante(val nombre: String, val carnet: String, val asignatura: String)

fun obtenerListaPDM(): String {
    val ciclo01 = mutableListOf<Estudiante>()
    // 3 de PDM
    ciclo01.add(Estudiante("Mario Molina", "00372624", "Programacion de Dispositivos Moviles"))
    ciclo01.add(Estudiante("Ricardo Pineda", "00378824", "Programacion de Dispositivos Moviles"))
    ciclo01.add(Estudiante("Miguel Escobar", "00401624", "Programacion de Dispositivos Moviles"))
    // 4 de Análisis Numérico
    ciclo01.add(Estudiante("Luis Tovar", "00101024", "Analisis Numerico"))
    ciclo01.add(Estudiante("Sofía Paz", "00202024", "Analisis Numerico"))
    ciclo01.add(Estudiante("Diego Sosa", "00303024", "Analisis Numerico"))
    ciclo01.add(Estudiante("Elena Roa", "00404024", "Analisis Numerico"))

    val soloPDM = ciclo01.filter { it.asignatura == "Programacion de Dispositivos Moviles" }
    return soloPDM.joinToString("\n") { "- ${it.nombre} (${it.carnet})" }
}
