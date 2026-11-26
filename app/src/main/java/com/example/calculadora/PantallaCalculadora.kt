package com.example.calculadora

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun PantallCalculadora(modifier: Modifier = Modifier) {
    var texto1 by remember { mutableStateOf("") }
    var texto2 by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("0") }
    var operacion by remember { mutableStateOf("+") }

    val numero1 = texto1.toIntOrNull() ?: 0
    val numero2 = texto2.toIntOrNull() ?: 0

    fun calcularOperacion() {
        resultado = when (operacion) {
            "+" -> (numero1 + numero2).toString()
            "-" -> (numero1 - numero2).toString()
            "*" -> (numero1 * numero2).toString()
            "/" -> if (numero2 != 0) (numero1 / numero2).toString() else "LA DIVISION NO SE HA CALCULADO!"
            else -> "0"
        }
        texto1 = ""
        texto2 = ""
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // TITULO
        Text(
            text = "CALCULADORA",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        // TEXTO 1
        TextField(
            value = texto1,
            label = { Text("PONER NUMERO 1") },
            onValueChange = { texto1 = it },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        // TEXTO 2
        TextField(
            value = texto2,
            label = { Text("PONER NUMERO 2") },
            onValueChange = { texto2 = it },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // TARJETA DE RESULTADO
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "RESULTADO = $resultado",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(16.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // suma resta division
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BotonImagen(
                idImagen = R.drawable.imagen_suma,
                descripcion = "Sumar",
                onClick = {
                    operacion = "+"
                    calcularOperacion()
                }
            )
            BotonImagen(
                idImagen = R.drawable.imagen_resta,
                descripcion = "Restar",
                onClick = {
                    operacion = "-"
                    calcularOperacion()
                }
            )
            BotonImagen(
                idImagen = R.drawable.imagen_division,
                descripcion = "Dividir",
                onClick = {
                    operacion = "/"
                    calcularOperacion()
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // multiplicacion y igual
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BotonImagen(
                idImagen = R.drawable.imagen_multiplicacion,
                descripcion = "Multiplicar",
                onClick = {
                    operacion = "*"
                    calcularOperacion()
                }
            )
            BotonImagen(
                idImagen = R.drawable.imagen_igual,
                descripcion = "Igual",
                onClick = { calcularOperacion() }
            )
        }
    }
}

@Composable
fun BotonImagen(
    idImagen: Int,
    descripcion: String,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(72.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(14.dp)
    ) {
        Image(
            painter = painterResource(id = idImagen),
            contentDescription = descripcion,
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxSize()
        )
    }
}