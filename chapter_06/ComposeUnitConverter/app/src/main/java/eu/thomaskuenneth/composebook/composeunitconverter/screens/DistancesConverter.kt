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
import eu.thomaskuenneth.composebook.composeunitconverter.viewmodels.DistancesViewModel

@Composable
fun DistancesConverter(viewModel: DistancesViewModel) {
    val strMeter = stringResource(id = R.string.meter)
    val strMile = stringResource(id = R.string.mile)
    val currentValue = viewModel.distance.observeAsState(viewModel.distance.value ?: "")
    val unit = viewModel.unit.observeAsState(viewModel.unit.value ?: R.string.meter)
    val convertedValue by viewModel.convertedDistance.observeAsState()
    val result by remember(convertedValue) {
        mutableStateOf(
            if (convertedValue?.isNaN() != false)
                ""
            else
                "$convertedValue ${
                    if (unit.value == R.string.meter)
                        strMile
                    else strMeter
                }"
        )
    }
    val calc = {
        viewModel.convert()
    }
    val enabled by remember(currentValue.value) {
        mutableStateOf(!viewModel.getDistanceAsFloat().isNaN())
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DistanceTextField(
            distance = currentValue,
            modifier = Modifier.padding(bottom = 16.dp),
            callback = calc,
            viewModel = viewModel
        )
        DistanceScaleButtonGroup(
            selected = unit,
            modifier = Modifier.padding(bottom = 16.dp)
        ) { resId: Int ->
            viewModel.setUnit(resId)
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
fun DistanceTextField(
    distance: State<String>,
    modifier: Modifier = Modifier,
    callback: () -> Unit,
    viewModel: DistancesViewModel
) {
    TextField(
        value = distance.value,
        onValueChange = {
            viewModel.setDistance(it)
        },
        placeholder = {
            Text(text = stringResource(id = R.string.placeholder_distance))
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
fun DistanceScaleButtonGroup(
    selected: State<Int>,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit
) {
    val sel = selected.value
    Row(modifier = modifier) {
        DistanceRadioButton(
            selected = sel == R.string.meter,
            resId = R.string.meter,
            onClick = onClick
        )
        DistanceRadioButton(
            selected = sel == R.string.mile,
            resId = R.string.mile,
            onClick = onClick,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

@Composable
fun DistanceRadioButton(
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