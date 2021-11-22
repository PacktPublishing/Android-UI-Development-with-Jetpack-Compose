package eu.thomaskuenneth.composebook.testinganddebuggingdemo

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SimpleInstrumentedTest {

    @get:Rule
    val rule = createComposeRule()

    @Before
    fun setup() {
        rule.setContent {
            SimpleButtonDemo()
        }
    }

    @Test
    fun testInitialColorOfBoxIsColor1() {
        rule.onNodeWithText("A").assertExists()
    }
}