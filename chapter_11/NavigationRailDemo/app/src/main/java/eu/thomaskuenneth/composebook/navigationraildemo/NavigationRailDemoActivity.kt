package eu.thomaskuenneth.composebook.navigationraildemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable

class NavigationRailDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationRailDemo()
        }
    }
}

@Composable
fun NavigationRailDemo() {
}