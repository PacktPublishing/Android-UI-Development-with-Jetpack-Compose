package eu.thomaskuenneth.composebook.testinganddebuggingdemo

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.rules.TestName

@RunWith(AndroidJUnit4::class)
class SimpleInstrumentedTest {

    @get:Rule
    var name = TestName()

    @get:Rule
    val rule = createComposeRule()

    @Before
    fun setup() {
        rule.setContent {
            SimpleButtonDemo()
        }
    }

    @Test
    fun testInitialLetterIsA() {
        rule.onNodeWithText("A").assertExists()
    }

    @Test
    fun testPrintMethodName() {
        println(name.methodName)
    }

    @Test
    fun testLetterAfterButtonClickIsB() {
        rule.onNodeWithText("A")
            .performClick()
            .assert(hasText("B"))
    }
}