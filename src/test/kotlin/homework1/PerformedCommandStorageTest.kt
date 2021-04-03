package homework1

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File
import java.nio.file.Path

internal class PerformedCommandStorageTest {
    private val storage = PerformedCommandStorage()

    @Test
    fun performAndCancel() {
        AddToBeginning(1).perform(storage)
        AddToBeginning(2).perform(storage)
        AddToEnd(0).perform(storage)
        storage.cancelAction()
        AddToEnd(3).perform(storage)
        AddToEnd(4).perform(storage)
        Move(0, 2).perform(storage)
        storage.cancelAction()
        assertEquals(listOf(2, 1, 3, 4), storage.numbers)
    }

    @Test
    fun doActionsFromFile() {
        storage.doActionsFromFile(javaClass.getResource("actionsFile.json").path)
        assertEquals(listOf(2, 1, 3, 4), storage.numbers)
    }

    @Test
    fun writeActionsToFile(@TempDir directory: Path) {
        val file = directory.resolve("testFile.json")
        AddToBeginning(1).perform(storage)
        AddToBeginning(2).perform(storage)
        AddToEnd(3).perform(storage)
        AddToEnd(4).perform(storage)
        storage.writeActionsToFile(file.toString())
        assertEquals(javaClass.getResource("actionsFile.json").readText(), File(file.toString()).readText())
    }
}