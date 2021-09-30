package eu.thomaskuenneth.composebook.statedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class StateDemoActivity : ComponentActivity() {
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
}
