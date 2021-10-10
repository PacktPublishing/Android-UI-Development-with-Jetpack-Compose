package eu.thomaskuenneth.composebook.composeunitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import eu.thomaskuenneth.composebook.composeunitconverter.theme.ComposeUnitConverterTheme

class ComposeUnitConverterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeUnitConverter()
        }
    }
}

@Composable
@Preview
fun ComposeUnitConverter() {
    ComposeUnitConverterTheme {
        Scaffold(topBar = {
            TopAppBar(title = {
                Text(text = stringResource(id = R.string.app_name))
            })
        }) {
            TemperatureConverter(viewModel = viewModel())
        }
    }
}
