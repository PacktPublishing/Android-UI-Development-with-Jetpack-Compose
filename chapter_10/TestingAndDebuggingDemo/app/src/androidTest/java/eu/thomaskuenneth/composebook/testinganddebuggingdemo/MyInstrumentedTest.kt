package eu.thomaskuenneth.composebook.testinganddebuggingdemo

import androidx.compose.material.Text
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MyInstrumentedTest {

    @get:Rule
    val myTestRule = createAndroidComposeRule<TestingAndDebuggingDemoActivity>()

    @Before
    fun setup() {
        myTestRule.setContent {
            TestingAndDebuggingDemo()
        }
        val rule2 = createComposeRule()
        rule2.setContent { Text("Hello") }
        rule2.onNodeWithText("Hello").assertExists()
    }

    @Test
    fun testToggleButtonPresent() {
        myTestRule.onNodeWithText("Toggle").assertExists()
    }

    @Test
    fun testToggleButtonNotPresent() {
        myTestRule.onNodeWithText("_Toggle").assertDoesNotExist()
    }
}