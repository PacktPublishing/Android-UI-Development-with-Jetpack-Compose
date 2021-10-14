package eu.thomaskuenneth.composebook.composeunitconverter

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// import androidx.compose.material.Typography
// import androidx.compose.ui.text.TextStyle
// import androidx.compose.ui.unit.sp
// import androidx.compose.foundation.shape.CutCornerShape
// import androidx.compose.material.Shapes
// import androidx.compose.ui.unit.dp

val AndroidGreen = Color(0xFF3DDC84)
val AndroidGreenDark = Color(0xFF20B261)
val Orange = Color(0xFFFFA500)
val OrangeDark = Color(0xFFCC8400)

private val DarkColorPalette = darkColors(
    primary = AndroidGreenDark,
    primaryVariant = AndroidGreenDark,
    secondary = OrangeDark,
    secondaryVariant = OrangeDark
)

private val LightColorPalette = lightColors(
    primary = AndroidGreen,
    primaryVariant = AndroidGreenDark,
    secondary = Orange,
    secondaryVariant = OrangeDark
)

@Composable
fun ComposeUnitConverterTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    MaterialTheme(
        colors = colors,
//        typography = Typography(button = TextStyle(fontSize = 24.sp)),
//        shapes = Shapes(small = CutCornerShape(8.dp)),
        content = content
    )
}

@Composable
@Preview
fun MaterialThemeDemo() {
    MaterialTheme(
        typography = Typography(
            h1 = TextStyle(color = Color.Red)
        )
    ) {
        Row {
            Text(
                text = "Hello",
                style = MaterialTheme.typography.h1
            )
            Spacer(modifier = Modifier.width(2.dp))
            MaterialTheme(
                typography = Typography(
                    h1 = TextStyle(color = Color.Blue)
                )
            ) {
                Text(
                    text = "Compose",
                    style = MaterialTheme.typography.h1
                )
            }
        }
    }
}