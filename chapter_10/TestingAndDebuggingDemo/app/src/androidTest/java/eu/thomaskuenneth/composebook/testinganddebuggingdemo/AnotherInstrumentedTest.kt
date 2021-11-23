package eu.thomaskuenneth.composebook.testinganddebuggingdemo

import androidx.compose.ui.test.assertWidthIsEqualTo
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AnotherInstrumentedTest {

    @get:Rule
    val rule = createAndroidComposeRule<TestingAndDebuggingDemoActivity>()

    @Test
    fun testImage() {
        rule.setContent {
            ImageDemo()
        }
        rule.onNodeWithContentDescription("Airport shuttle")
            .assertWidthIsEqualTo(128.dp)
    }
}