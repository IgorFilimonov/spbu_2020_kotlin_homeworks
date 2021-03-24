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

enum class Commands(val value: Int) {
    ADD_TO_BEGINNING(1),
    ADD_TO_END(2),
    MOVE(3),
    CANCEL_ACTION(4),
    PRINT_NUMBERS(5)
}

fun intInput(message: String): Int {
    println(message)
    return readLine()?.toIntOrNull() ?: throw IllegalArgumentException("This is not an integer")
}

fun executeCommand(typeOfCommand: Int?, storage: PerformedCommandStorage) {
    when (typeOfCommand) {
        Commands.ADD_TO_BEGINNING.value -> AddToBeginning(intInput("Enter value:"), storage).perform()
        Commands.ADD_TO_END.value -> AddToEnd(intInput("Enter value:"), storage).perform()
        Commands.MOVE.value -> Move(intInput("Enter start index:"), intInput("Enter end index:"), storage).perform()
        Commands.CANCEL_ACTION.value -> storage.cancelAction()
        Commands.PRINT_NUMBERS.value -> {
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
