package eu.thomaskuenneth.composebook.animationdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class AnimationDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // StateChangeDemo()
            // SingleValueAnimationDemo()
            // MultipleValuesAnimationDemo()
            // AnimatedVisibilityDemo()
            // SizeChangeAnimationDemo()
            // CrossfadeAnimationDemo()
            InfiniteRepeatableDemo()
        }
    }
}

@Preview
@Composable
fun StateChangeDemo() {
    var toggled by remember {
        mutableStateOf(false)
    }
    val color = if (toggled)
        Color.White
    else
        Color.Red
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            toggled = !toggled
        }) {
            Text(
                stringResource(R.string.toggle)
            )
        }
        Box(
            modifier = Modifier
                .padding(top = 32.dp)
                .background(color = color)
                .size(128.dp)
        )
    }
}

@Composable
@Preview
fun SingleValueAnimationDemo() {
    var toggled by remember {
        mutableStateOf(false)
    }
    val color by animateColorAsState(
        targetValue = if (toggled)
            Color.White
        else
            Color.Red,
        animationSpec = spring(stiffness = Spring.StiffnessVeryLow)
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            toggled = !toggled
        }) {
            Text(
                stringResource(R.string.toggle)
            )
        }
        Box(
            modifier = Modifier
                .padding(top = 32.dp)
                .background(color = color)
                .size(128.dp)
        )
    }
}

@Composable
@Preview
fun MultipleValuesAnimationDemo() {
    var toggled by remember {
        mutableStateOf(false)
    }
    val transition = updateTransition(
        targetState = toggled,
        label = "toggledTransition"
    )
    val borderWidth by transition.animateDp(label = "borderWidthTransition") { state ->
        if (state)
            10.dp
        else
            1.dp
    }
    val degrees by transition.animateFloat(label = "degreesTransition") { state ->
        if (state) -90F
        else
            0F
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            toggled = !toggled
        }) {
            Text(
                stringResource(R.string.toggle)
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(top = 32.dp)
                .border(
                    width = borderWidth,
                    color = Color.Black
                )
                .size(128.dp)
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                modifier = Modifier.rotate(degrees = degrees)
            )
        }
    }
}

@Composable
@Preview
fun AnimatedVisibilityDemo() {
    var visible by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            visible = !visible
        }) {
            Text(
                stringResource(
                    id = if (visible)
                        R.string.hide
                    else
                        R.string.show
                )
            )
        }
        AnimatedVisibility(
            visible = visible,
            enter = slideInHorizontally(),
            exit = slideOutVertically()
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 32.dp)
                    .background(color = Color.Red)
                    .size(128.dp)
            )
        }
    }
}

@Preview
@Composable
fun SizeChangeAnimationDemo() {
    var size by remember { mutableStateOf(1F) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Slider(
            value = size,
            valueRange = (1F..4F),
            steps = 3,
            onValueChange = {
                size = it
            },
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = stringResource(id = R.string.lines),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .animateContentSize(),
            maxLines = size.toInt(), color = Color.Blue
        )
    }
}

@Preview
@Composable
fun CrossfadeAnimationDemo() {
    var isFirstScreen by remember { mutableStateOf(true) }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Switch(
            checked = isFirstScreen,
            onCheckedChange = {
                isFirstScreen = !isFirstScreen
            },
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
        )
        Crossfade(targetState = isFirstScreen) {
            if (it) {
                Screen(
                    text = stringResource(id = R.string.letter_w),
                    backgroundColor = Color.Gray
                )
            } else {
                Screen(
                    text = stringResource(id = R.string.letter_i),
                    backgroundColor = Color.LightGray
                )
            }
        }
    }
}

@Composable
fun Screen(
    text: String,
    backgroundColor: Color = Color.White
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.h1
        )
    }
}

@Composable
fun InfiniteRepeatableDemo() {
    val infiniteTransition = rememberInfiniteTransition()
    val degrees by infiniteTransition.animateFloat(
        initialValue = 0F,
        targetValue = 359F,
        animationSpec = infiniteRepeatable(animation = keyframes {
            durationMillis = 1500
            0F at 0
            359F at 1500
        })
    )
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = stringResource(id = R.string.app_name),
        modifier = Modifier.rotate(degrees = degrees))
    }
}