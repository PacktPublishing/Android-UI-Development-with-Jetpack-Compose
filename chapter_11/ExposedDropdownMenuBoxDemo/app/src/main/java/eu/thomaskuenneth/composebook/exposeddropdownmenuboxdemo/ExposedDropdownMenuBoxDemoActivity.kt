package eu.thomaskuenneth.composebook.exposeddropdownmenuboxdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@ExperimentalMaterialApi
class ExposedDropdownMenuBoxDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExposedDropdownMenuBoxDemo()
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun ExposedDropdownMenuBoxDemo() {
    val titles = List(3) { i ->
        stringResource(id = R.string.item, i + 1)
    }
    var expanded by remember { mutableStateOf(false) }
    var selectedTxt by remember { mutableStateOf(titles[0]) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        ExposedDropdownMenuBox(expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }) {
            TextField(value = selectedTxt,
                onValueChange = { },
                readOnly = true,
                label = {
                    Text(text = stringResource(id = R.string.label))
                },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                }
            )
            ExposedDropdownMenu(expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }) {
                for (title in titles) {
                    DropdownMenuItem(onClick = {
                        expanded = false
                        selectedTxt = title
                    }) {
                        Text(text = title)
                    }
                }
            }
        }
    }
}