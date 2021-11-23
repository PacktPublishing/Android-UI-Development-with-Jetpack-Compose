package eu.thomaskuenneth.composebook.testinganddebuggingdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

fun isEven(num: Int): Boolean {
    val div2 = num / 2
    return (div2 * 2) == num
}

class TestingAndDebuggingDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // SimpleButtonDemo()
            ImageDemo()
        }
    }
}

@Composable
fun SimpleButtonDemo() {
    val a = stringResource(id = R.string.a)
    val b = stringResource(id = R.string.b)
    var text by remember { mutableStateOf(a) }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = {
            text = if (text == a) b else a
        }) {
            Text(text = text)
        }
    }
}

@Composable
fun ImageDemo() {
    Image(
        painter = painterResource(id = R.drawable.ic_baseline_airport_shuttle_24),
        contentDescription = stringResource(id = R.string.airport_shuttle),
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .size(width = 128.dp, height = 128.dp)
            .background(Color.Blue)
    )
}