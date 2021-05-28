package finalwork

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

internal class BubbleSortTest {
    @Test
    fun testIntList() {
        val testList = mutableListOf(2, 1, 3, 5, 4)
        val testComparator = object : Comparator<Int> {
            override fun compare(o1: Int?, o2: Int?): Int = o1!! - o2!!
        }
        assertEquals(testList.bubbleSort(testComparator), mutableListOf(1, 2, 3, 4, 5))
    }

    @Test
    fun testStringList() {
        val testList = mutableListOf("aa", "a", "b")
        val testComparator = object : Comparator<String> {
            override fun compare(o1: String?, o2: String?): Int = o1!!.compareTo(o2!!)
        }
        assertEquals(testList.bubbleSort(testComparator), mutableListOf("a", "aa", "b"))
    }
}