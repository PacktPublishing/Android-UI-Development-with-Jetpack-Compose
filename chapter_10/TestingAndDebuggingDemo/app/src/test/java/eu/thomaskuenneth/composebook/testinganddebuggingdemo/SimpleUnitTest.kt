package eu.thomaskuenneth.composebook.testinganddebuggingdemo

import org.junit.*
import org.junit.Assert.assertEquals

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
    fun testIsEvenZero() {
        assertEquals(true, isEven(0))
    }

    @Test
    fun testListOfInts() {
        val nums = listOf(Int.MIN_VALUE, -3, -2, 2, 3, Int.MAX_VALUE)
        val results = listOf(true, false, true, true, false, false)
        nums.forEachIndexed { index, num ->
            val result = isEven(num)
            println("isEven($num) returns $result")
            assertEquals(result, results[index])
        }
    }
}