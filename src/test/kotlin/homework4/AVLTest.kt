package homework4

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

internal class AVLTest {
    private val AVLTree = AVL<Int, String>()

    @Test
    fun testContainsKey() {
        AVLTree.put(2, "b")
        AVLTree.put(1, "a")
        AVLTree.put(4, "d")
        AVLTree.put(3, "c")
        AVLTree.remove(2)
        assertEquals(setOf(true, false), setOf(AVLTree.containsKey(4), AVLTree.containsKey(2)))
    }

    @Test
    fun testContainsValue() {
        AVLTree.put(2, "b")
        AVLTree.put(1, "a")
        AVLTree.put(4, "d")
        AVLTree.remove(2)
        AVLTree.put(3, "c")
        AVLTree.remove(3)
        AVLTree.remove(4)
        assertEquals(setOf(true, false), setOf(AVLTree.containsValue("a"), AVLTree.containsValue("d")))
    }

    @Test
    fun testEntries() {
        AVLTree.put(1, "a")
        AVLTree.put(2, "b")
        assertEquals(mutableMapOf(1 to "a", 2 to "b").entries, AVLTree.entries)
    }

    @Test
    fun testGet() {
        AVLTree.put(2, "b")
        AVLTree.put(1, "a")
        AVLTree.put(4, "d")
        AVLTree.remove(2)
        AVLTree.put(3, "c")
        AVLTree.remove(3)
        AVLTree.remove(4)
        assertEquals(setOf("a", null), setOf(AVLTree.get(1), AVLTree.get(4)))
    }

    @Test
    fun testIsEmpty() {
        AVLTree.put(1, "a")
        AVLTree.put(2, "b")
        AVLTree.remove(1)
        AVLTree.remove(2)
        assertEquals(true, AVLTree.isEmpty())
    }

    @Test
    fun testPut() {
        AVLTree.put(22, "b")
        AVLTree.put(19, "a")
        AVLTree.put(40, "d")
        AVLTree.put(333, "c")
        assertEquals(true, AVLTree.containsKey(19))
    }

    @Test
    fun testRemove() {
        AVLTree.put(22, "b")
        AVLTree.put(19, "a")
        AVLTree.put(40, "d")
        AVLTree.put(333, "c")
        AVLTree.remove(17)
        AVLTree.remove(19)
        AVLTree.remove(333)
        assertEquals(setOf(false, false), setOf(AVLTree.containsKey(19), AVLTree.containsKey(333)))
    }

    @Test
    fun testSize() {
        AVLTree.put(2, "b")
        AVLTree.put(1, "a")
        AVLTree.put(4, "d")
        AVLTree.remove(2)
        AVLTree.put(3, "c")
        AVLTree.remove(3)
        AVLTree.remove(4)
        assertEquals(1, AVLTree.size)
    }
}