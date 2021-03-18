package homework1

import homework1.PerformedCommandStorage.PerformedCommandStorage
import homework1.PerformedCommandStorage.AddToBeginning
import homework1.PerformedCommandStorage.AddToEnd
import homework1.PerformedCommandStorage.MoveElement

fun displayHints() {
    println("What do you want to do?\n" +
            "1. Insert element x at the beginning of the list\n" +
            "2. Insert element x at the end of the list\n" +
            "3. Move element from i to j position\n" +
            "4. Undo the last action (except printing)\n" +
            "5. Print elements\n" +
            "0. Shut down\n" +
            "To select, enter the command number:")
}

fun executeCommand(typeOfCommand: Int?, storage: PerformedCommandStorage) {
    when (typeOfCommand) {
        1 -> {
            println("Enter value:")
            val value = readLine()?.toIntOrNull() ?: throw IllegalArgumentException("This is not an integer")
            AddToBeginning(value, storage).perform()
        }
        2 -> {
            println("Enter value:")
            val value = readLine()?.toIntOrNull() ?: throw IllegalArgumentException("This is not an integer")
            AddToEnd(value, storage).perform()
        }
        3 -> {
            println("Enter start index:")
            val startIndex = readLine()?.toIntOrNull() ?: throw IllegalArgumentException("This is not an integer")
            println("Enter end index:")
            val endIndex = readLine()?.toIntOrNull() ?: throw IllegalArgumentException("This is not an integer")
            MoveElement(startIndex, endIndex, storage).perform()
        }
        4 -> {
            storage.cancelAction()
        }
        5 -> {
            println("Numbers:")
            storage.printNumbers()
        }
        null -> throw IllegalArgumentException("This is not an integer")
        else -> {}
    }
}

fun main() {
    var command: Int? = -1
    val storage = PerformedCommandStorage()
    while (command != 0) {
        displayHints()
        command = readLine()?.toIntOrNull()
        executeCommand(command, storage)
    }
}
