package retest2

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

internal class CyclicListTest {
    val testList = CyclicList<Int>()

    @Test
    fun testIsEmpty() = assertEquals(true, testList.isEmpty())

    @Test
    fun testAdd() {
        testList.add(1, 0)
        testList.add(2, 1)
        testList.add(3, 0)
        assertEquals(mutableListOf(3, 1, 2), testList.getRepresentationForTest())
    }

    @Test
    fun testRemove() {
        testList.add(1, 0)
        testList.add(2, 1)
        testList.add(3, 2)
        testList.add(4, 3)
        testList.remove(1)
        testList.remove(1)
        assertEquals(mutableListOf(1, 4), testList.getRepresentationForTest())
    }

    @Test
    fun testGet() {
        testList.add(1, 0)
        testList.add(2, 1)
        testList.add(3, 2)
        testList.add(4, 3)
        assertEquals(3, testList.get(2))
    }

    @Test
    fun testJump() {
        testList.add(1, 0)
        testList.add(2, 1)
        testList.add(3, 2)
        testList.add(4, 3)
        assertEquals(3, testList.jump(0, 6))
    }
}