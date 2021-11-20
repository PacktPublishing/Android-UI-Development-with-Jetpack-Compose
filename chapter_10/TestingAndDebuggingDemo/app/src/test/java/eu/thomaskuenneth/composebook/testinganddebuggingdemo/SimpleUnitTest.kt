package eu.thomaskuenneth.composebook.testinganddebuggingdemo

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

class SimpleUnitTest {

    companion object {
        @BeforeClass
        @JvmStatic
        fun setupAll() {
            println("Setting things up")
        }
    }

    @Before
    fun setup() {
        println("Setup test")
    }

    @After
    fun teardown() {
        println("Clean up test")
    }

    @Test
    fun testIsEven2() {
        println("running testIsEven2()")
        assertEquals(isEven(2), true)
    }

    @Test
    fun testIsEven3() {
        println("running testIsEven3()")
        assertNotEquals(isEven(3), true)
    }
}