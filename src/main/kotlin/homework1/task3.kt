package homework1

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

const val COMMAND_TYPE1 = 1
const val COMMAND_TYPE2 = 2
const val COMMAND_TYPE3 = 3
const val COMMAND_TYPE4 = 4
const val COMMAND_TYPE5 = 5

fun executeCommand(typeOfCommand: Int?, storage: PerformedCommandStorage) {
    when (typeOfCommand) {
        COMMAND_TYPE1 -> {
            println("Enter value:")
            val value = readLine()?.toIntOrNull()
            AddToBeginning(value, storage).perform()
        }
        COMMAND_TYPE2 -> {
            println("Enter value:")
            val value = readLine()?.toIntOrNull()
            AddToEnd(value, storage).perform()
        }
        COMMAND_TYPE3 -> {
            println("Enter start index:")
            val startIndex = readLine()?.toIntOrNull()
            println("Enter end index:")
            val endIndex = readLine()?.toIntOrNull()
            MoveElement(startIndex, endIndex, storage).perform()
        }
        COMMAND_TYPE4 -> {
            storage.cancelAction()
        }
        COMMAND_TYPE5 -> {
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
