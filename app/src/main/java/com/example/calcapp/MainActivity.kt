package com.example.calcapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.calcapp.ui.theme.CalcAppTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalcAppTheme {
                    Calculator(

                    )

            }
        }
    }
}



@Composable
fun Calculator() {
    var number1 by remember { mutableStateOf("") }
    var number2 by remember { mutableStateOf("") }
    var operation by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = number1,
            onValueChange = { number1 = it },
            label = { Text("Number 1") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TextField(
            value = number2,
            onValueChange = { number2 = it },
            label = { Text("Number 2") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OperationButton("+") { operation = "+" }
            OperationButton("-") { operation = "-" }
            OperationButton("*") { operation = "*" }
            OperationButton("/") { operation = "/" }
        }
        Button(onClick = {
            val n1 = number1.toDoubleOrNull() ?: 0.0
            val n2 = number2.toDoubleOrNull() ?: 0.0
            result = when (operation) {
                "+" -> (n1 + n2).toString()
                "-" -> (n1 - n2).toString()
                "*" -> (n1 * n2).toString()
                "/" -> if (n2 != 0.0) (n1 / n2).toString() else "Error"
                else -> ""
            }
        }) {
            Text("Calculate")
        }
        Text("Result: $result")
    }
}

@Composable
fun OperationButton(op: String, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(op)
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
    Calculator()
}