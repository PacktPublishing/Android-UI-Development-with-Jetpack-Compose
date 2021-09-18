package eu.thomaskuenneth.composebook.constraintlayoutdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@ExperimentalComposeUiApi
class ConstraintLayoutDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConstraintLayoutDemo()
        }
    }
}

@ExperimentalComposeUiApi
@Composable
@Preview
fun ConstraintLayoutDemo() {
    val red = remember { mutableStateOf(true) }
    val green = remember { mutableStateOf(true) }
    val blue = remember { mutableStateOf(true) }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val (cbRed, cbGreen, cbBlue, boxRed, boxGreen, boxBlue) = createRefs()
        CheckboxWithLabel(
            label = stringResource(id = R.string.red),
            state = red,
            modifier = Modifier.constrainAs(cbRed) {
                top.linkTo(parent.top)
            }
        )
        CheckboxWithLabel(
            label = stringResource(id = R.string.green),
            state = green,
            modifier = Modifier.constrainAs(cbGreen) {
                top.linkTo(cbRed.bottom)
            }
        )
        CheckboxWithLabel(
            label = stringResource(id = R.string.blue),
            state = blue,
            modifier = Modifier.constrainAs(cbBlue) {
                top.linkTo(cbGreen.bottom)
            }
        )
        if (red.value) {
            Box(
                modifier = Modifier
                    .background(Color.Red)
                    .constrainAs(boxRed) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(cbBlue.bottom, margin = 16.dp)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    }
            )
        }
        if (green.value) {
            Box(
                modifier = Modifier
                    .background(Color.Green)
                    .constrainAs(boxGreen) {
                        start.linkTo(parent.start, margin = 32.dp)
                        end.linkTo(parent.end, margin = 32.dp)
                        top.linkTo(cbBlue.bottom, margin = (16 + 32).dp)
                        bottom.linkTo(parent.bottom, margin = 32.dp)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    }
            )
        }
        if (blue.value) {
            Box(
                modifier = Modifier
                    .background(Color.Blue)
                    .constrainAs(boxBlue) {
                        start.linkTo(parent.start, margin = 64.dp)
                        end.linkTo(parent.end, margin = 64.dp)
                        top.linkTo(cbBlue.bottom, margin = (16 + 64).dp)
                        bottom.linkTo(parent.bottom, margin = 64.dp)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    }
            )
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun CheckboxWithLabel(
    label: String,
    state: MutableState<Boolean>,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(modifier = modifier.clickable {
        state.value = !state.value
    }) {
        val (checkbox, text) = createRefs()
        Checkbox(
            checked = state.value,
            onCheckedChange = {
                state.value = it
            },
            modifier = Modifier.constrainAs(checkbox) {
            }
        )
        Text(
            text = label,
            modifier = Modifier.constrainAs(text) {
                start.linkTo(checkbox.end, margin = 8.dp)
                top.linkTo(checkbox.top)
                bottom.linkTo(checkbox.bottom)
            }
        )
    }
}
