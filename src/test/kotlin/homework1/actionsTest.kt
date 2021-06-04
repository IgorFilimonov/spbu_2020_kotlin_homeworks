package homework1

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

internal class AddToBeginningTest {
    private val storage = PerformedCommandStorage()

    @Test
    fun performAction() {
        AddToBeginning(4).perform(storage)
        AddToBeginning(2).perform(storage)
        AddToBeginning(1).perform(storage)
        assertEquals(listOf(1, 2, 4), storage.numbers)
    }

    @Test
    fun cancelAction() {
        AddToBeginning(3).perform(storage)
        AddToBeginning(2).perform(storage)
        AddToBeginning(1).perform(storage)
        storage.cancelAction()
        assertEquals(listOf(2, 3), storage.numbers)
    }
}

internal class AddToEndTest {
    private val storage = PerformedCommandStorage()

    @Test
    fun performAction() {
        AddToEnd(1).perform(storage)
        AddToEnd(2).perform(storage)
        AddToEnd(3).perform(storage)
        assertEquals(listOf(1, 2, 3), storage.numbers)
    }

    @Test
    fun cancelAction() {
        AddToEnd(1).perform(storage)
        AddToEnd(2).perform(storage)
        AddToEnd(3).perform(storage)
        storage.cancelAction()
        assertEquals(listOf(1, 2), storage.numbers)
    }
}

internal class MoveTest {
    private val storage = PerformedCommandStorage()

    @Test
    fun performAction() {
        AddToEnd(1).perform(storage)
        AddToEnd(2).perform(storage)
        AddToEnd(3).perform(storage)
        Move(0, 2).perform(storage)
        assertEquals(listOf(2, 3, 1), storage.numbers)
    }

    @Test
    fun cancelAction() {
        AddToEnd(1).perform(storage)
        AddToEnd(2).perform(storage)
        AddToEnd(3).perform(storage)
        Move(0, 2).perform(storage)
        storage.cancelAction()
        assertEquals(listOf(1, 2, 3), storage.numbers)
    }

    @Test
    fun testInvalidIndices() {
        AddToEnd(1).perform(storage)
        AddToEnd(2).perform(storage)
        AddToEnd(3).perform(storage)
        assertThrows(ArrayIndexOutOfBoundsException("Invalid indices")::class.java) {
            Move(0, 3).perform(storage)
        }
    }
}