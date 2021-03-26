package homework1

fun displayHints() {
    println("What do you want to do?\n" +
            "1. Insert element x at the beginning of the list\n" +
            "2. Insert element x at the end of the list\n" +
            "3. Move element from i to j position\n" +
            "4. Undo the last action (except printing)\n" +
            "5. Print elements\n" +
            "6. Do actions from json-file\n" +
            "7. Save already applied actions to json-file\n" +
            "0. Shut down\n" +
            "To select, enter the command number:")
}

enum class Commands {
    ADD_TO_BEGINNING,
    ADD_TO_END,
    MOVE,
    CANCEL_ACTION,
    PRINT_NUMBERS,
    DO_ACTIONS_FROM_FILE,
    WRITE_ACTIONS_TO_FILE
}

fun intInput(message: String): Int {
    println(message)
    return readLine()?.toIntOrNull() ?: error("This is not an integer")
}

fun executeCommand(typeOfCommand: Int, storage: PerformedCommandStorage) {
    when (typeOfCommand - 1) {
        Commands.ADD_TO_BEGINNING.ordinal -> AddToBeginning(intInput("Enter value:")).perform(storage)
        Commands.ADD_TO_END.ordinal -> AddToEnd(intInput("Enter value:")).perform(storage)
        Commands.MOVE.ordinal -> Move(intInput("Enter start index:"), intInput("Enter end index:")).perform(storage)
        Commands.CANCEL_ACTION.ordinal -> storage.cancelAction()
        Commands.PRINT_NUMBERS.ordinal -> {
            println("Numbers:")
            storage.printNumbers()
        }
        Commands.DO_ACTIONS_FROM_FILE.ordinal -> {
            println("Enter the file path:")
            val inputFile = readLine() ?: error("This is not a string")
            storage.doActionsFromFile(inputFile)
        }
        Commands.WRITE_ACTIONS_TO_FILE.ordinal -> {
            println("Enter the file path:")
            val outputFile = readLine() ?: error("This is not a string")
            storage.writeActionsToFile(outputFile)
        }
        else -> error("Invalid command")
    }
}

fun main() {
    var command: Int? = -1
    val storage = PerformedCommandStorage()
    while (command != 0) {
        displayHints()
        command = readLine()?.toIntOrNull() ?: error("This is not an integer")
        if (command != 0) {
            executeCommand(command, storage)
        }
    }
}
