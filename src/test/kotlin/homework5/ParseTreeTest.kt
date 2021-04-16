package homework5

import homework3.Task3KtTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ParseTreeTest {
    @Test
    fun testDefaultCase() {
        val tree = ParseTree(ParseTreeTest::class.java.getResource("testDefaultCase/input.txt").path.toString())
        tree.build()
        val expected = ParseTreeTest::class.java.getResource("testDefaultCase/expected.txt").readText().replace("\r", "")
        assertEquals(expected, tree.outputForDebugging())
    }

    @Test
    fun testHarderCase() {
        val tree = ParseTree(ParseTreeTest::class.java.getResource("testHarderCase/input.txt").path.toString())
        tree.build()
        val expected = ParseTreeTest::class.java.getResource("testHarderCase/expected.txt").readText().replace("\r", "")
        assertEquals(expected, tree.outputForDebugging())
    }

    @Test
    fun testDivisionByZero() {
        val tree = ParseTree(ParseTreeTest::class.java.getResource("testDivisionByZero/input.txt").path.toString())
        tree.build()
        assertThrows(ArithmeticException("You can't divide by zero!")::class.java) {
            tree.outputForDebugging()
        }
    }

    @Test
    fun oneMoreTest() {
        val tree = ParseTree(ParseTreeTest::class.java.getResource("oneMoreTest/input.txt").path.toString())
        tree.build()
        val expected = ParseTreeTest::class.java.getResource("oneMoreTest/expected.txt").readText().replace("\r", "")
        assertEquals(expected, tree.outputForDebugging())
    }
}