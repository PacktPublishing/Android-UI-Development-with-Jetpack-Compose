package eu.thomaskuenneth.composebook.hello

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Factorial()
        }
    }
}

@Composable
fun Factorial() {
    var expanded by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf(factorialAsString(0)) }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.clickable {
                expanded = true
            },
            text = text,
            style = MaterialTheme.typography.h2
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }) {
            for (n in 0 until 10) {
                DropdownMenuItem(onClick = {
                    expanded = false
                    text = factorialAsString(n)
                }) {
                    Text("${n.toString()}!")
                }
            }
        }
    }
}

fun factorialAsString(n: Int): String {
    var result = 1L
    for (i in 1..n) {
        result *= i
    }
    return "$n! = $result"
}