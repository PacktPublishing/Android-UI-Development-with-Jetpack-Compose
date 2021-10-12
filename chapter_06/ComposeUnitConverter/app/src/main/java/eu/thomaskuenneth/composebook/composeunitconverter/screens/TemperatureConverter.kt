package eu.thomaskuenneth.composebook.composeunitconverter.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import eu.thomaskuenneth.composebook.composeunitconverter.R
import eu.thomaskuenneth.composebook.composeunitconverter.viewmodels.TemperatureViewModel

@Composable
fun TemperatureConverter(viewModel: TemperatureViewModel) {
    val strCelsius = stringResource(id = R.string.celsius)
    val strFahrenheit = stringResource(id = R.string.fahrenheit)
    val currentValue = viewModel.temperature.observeAsState(viewModel.temperature.value ?: "")
    val scale = viewModel.scale.observeAsState(viewModel.scale.value ?: R.string.celsius)
    var result by remember { mutableStateOf("") }
    val calc = {
        val temp = viewModel.convert()
        result = if (temp.isNaN())
            ""
        else
            "$temp${
                if (scale.value == R.string.celsius)
                    strFahrenheit
                else strCelsius
            }"
    }
    val enabled by remember(currentValue.value) {
        mutableStateOf(!viewModel.getTemperatureAsFloat().isNaN())
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TemperatureTextField(
            temperature = currentValue,
            modifier = Modifier.padding(bottom = 16.dp),
            callback = calc,
            viewModel = viewModel
        )
        TemperatureScaleButtonGroup(
            selected = scale,
            modifier = Modifier.padding(bottom = 16.dp)
        ) { resId: Int ->
            viewModel.setScale(resId)
        }
        Button(
            onClick = calc,
            enabled = enabled
        ) {
            Text(text = stringResource(id = R.string.convert))
        }
        if (result.isNotEmpty()) {
            Text(
                text = result,
                style = MaterialTheme.typography.h3
            )
        }
    }
}

@Composable
fun TemperatureTextField(
    temperature: State<String>,
    modifier: Modifier = Modifier,
    callback: () -> Unit,
    viewModel: TemperatureViewModel
) {
    TextField(
        value = temperature.value,
        onValueChange = {
            viewModel.setTemperature(it)
        },
        placeholder = {
            Text(text = stringResource(id = R.string.placeholder))
        },
        modifier = modifier,
        keyboardActions = KeyboardActions(onAny = {
            callback()
        }),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        singleLine = true
    )
}

@Composable
fun TemperatureScaleButtonGroup(
    selected: State<Int>,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit
) {
    val sel = selected.value
    Row(modifier = modifier) {
        TemperatureRadioButton(
            selected = sel == R.string.celsius,
            resId = R.string.celsius,
            onClick = onClick
        )
        TemperatureRadioButton(
            selected = sel == R.string.fahrenheit,
            resId = R.string.fahrenheit,
            onClick = onClick,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

@Composable
fun TemperatureRadioButton(
    selected: Boolean,
    resId: Int,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        RadioButton(
            selected = selected,
            onClick = {
                onClick(resId)
            }
        )
        Text(
            text = stringResource(resId),
            modifier = Modifier
                .padding(start = 8.dp)
        )
    }
}