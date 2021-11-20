package eu.thomaskuenneth.composebook.testinganddebuggingdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource

fun isEven(num: Int): Boolean {
    val div2 = num / 2
    return (div2 * 2) == num
}

class TestingAndDebuggingDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleButtonDemo()
        }
    }
}

val COLOR1 = Color.White
val COLOR2 = Color.LightGray

@Composable
fun SimpleButtonDemo() {
    var color by remember { mutableStateOf(COLOR1) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = color),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = {
            color = if (color == COLOR1)
                COLOR2
            else
                COLOR1
        }) {
            Text(text = stringResource(id = R.string.toggle))
        }
    }
}