package eu.thomaskuenneth.composebook.predefinedlayoutsdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class PredefinedLayoutsDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PredefinedLayoutsDemo()
        }
    }
}

@Composable
@Preview
fun PredefinedLayoutsDemo() {
    val red = remember { mutableStateOf(true) }
    val green = remember { mutableStateOf(true) }
    val blue = remember { mutableStateOf(true) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        CheckboxWithLabel(
            label = stringResource(id = R.string.red),
            state = red
        )
        CheckboxWithLabel(
            label = stringResource(id = R.string.green),
            state = green
        )
        CheckboxWithLabel(
            label = stringResource(id = R.string.blue),
            state = blue
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp)
        ) {
            if (red.value) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Red)
                )
            }
            if (green.value) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                        .background(Color.Green)
                )
            }
            if (blue.value) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(64.dp)
                        .background(Color.Blue)
                )
            }
        }
    }
}

@Composable
fun CheckboxWithLabel(label: String, state: MutableState<Boolean>) {
    Row(
        modifier = Modifier.clickable {
            state.value = !state.value
        }, verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = state.value,
            onCheckedChange = {
                state.value = it
            }
        )
        Text(
            text = label,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}
