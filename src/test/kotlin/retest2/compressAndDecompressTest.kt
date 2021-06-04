package retest2

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.lang.IllegalArgumentException

internal class compressAndDecompressTest {
    @Test
    fun testCompress() {
        val testArray = byteArrayOf(1, 1, 2, 3)
        assertEquals(byteArrayOf(2, 1, 1, 2, 1, 3).toList(), testArray.compress().toList())
    }

    @Test
    fun testDecompress() {
        val testArray = byteArrayOf(2, 1, 1, 2, 1, 3)
        assertEquals(byteArrayOf(1, 1, 2, 3).toList(), testArray.decompress().toList())
    }

    @Test
    fun testDecompressOddSizeArray() {
        val testArray = byteArrayOf(2, 1, 1)
        assertThrows(IllegalArgumentException("Array size must be an even number")::class.java) {
            testArray.decompress()
        }
    }

    @Test
    fun testDecompressArrayWithNonPositiveNumbers() {
        val testArray = byteArrayOf(-2, 1, 1, 2)
        assertThrows(IllegalArgumentException("The byte value denoting quantity must be non-negative")::class.java) {
            testArray.decompress()
        }
    }
}