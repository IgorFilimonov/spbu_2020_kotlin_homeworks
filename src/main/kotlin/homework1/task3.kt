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

const val ADD_TO_BEGINNING = 1
const val ADD_TO_END = 2
const val MOVE_ELEMENT = 3
const val CANCEL_ACTION = 4
const val PRINT_NUMBERS = 5

fun numberInput(message: String): Int {
    println(message)
    return readLine()?.toIntOrNull() ?: throw IllegalArgumentException("This is not an integer")
}

fun executeCommand(typeOfCommand: Int?, storage: PerformedCommandStorage) {
    when (typeOfCommand) {
        ADD_TO_BEGINNING -> AddToBeginning(numberInput("Enter value:"), storage).perform()
        ADD_TO_END -> AddToEnd(numberInput("Enter value:"), storage).perform()
        MOVE_ELEMENT -> Move(numberInput("Enter start index:"), numberInput("Enter end index:"), storage).perform()
        CANCEL_ACTION -> storage.cancelAction()
        PRINT_NUMBERS -> {
            println("Numbers:")
            storage.printNumbers()
        }
        else -> throw IllegalArgumentException("This is not an integer")
    }
}

fun main() {
    var command: Int? = -1
    val storage = PerformedCommandStorage()
    while (command != 0) {
        displayHints()
        command = readLine()?.toIntOrNull()
        if (command != 0) {
            executeCommand(command, storage)
        }
    }
}
