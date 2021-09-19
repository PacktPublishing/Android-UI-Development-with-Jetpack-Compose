package eu.thomaskuenneth.composebook.customlayoutdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@ExperimentalComposeUiApi
class ConstraintLayoutDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ColumnWithTexts()
        }
    }
}

@Composable
@Preview
fun ColumnWithTexts() {
    Column {
        Text(
            text = "Android UI development with Jetpack Compose",
            style = MaterialTheme.typography.h3,
        )
        Text(
            text = "Hello Compose",
            style = MaterialTheme
                .typography.h5.merge(TextStyle(color = Color.Red))
        )
    }
}