package test1

fun displayHints() {
    println("What do you want to do?\n" +
            "1. Add element\n" +
            "2. Find out if there is an element\n" +
            "3. Remove element\n" +
            "4. Find out the number of elements\n" +
            "5. Find out the number of elements starting with a given prefix\n" +
            "0. Shut down\n" +
            "To select, enter the command number:")
}

enum class Commands {
    ADD,
    CONTAINS,
    REMOVE,
    SIZE,
    HOW_MANY_START_WITH_PREFIX
}

fun stringInput(message: String): String {
    println(message)
    return readLine().toString()
}

fun executeCommand(typeOfCommand: Int, trie: Trie) {
    when (typeOfCommand - 1) {
        Commands.ADD.ordinal -> {
            if (trie.add(stringInput("Enter the string:"))) {
                println("This element already exists")
            }
        }
        Commands.CONTAINS.ordinal -> {
            if (trie.contains(stringInput("Enter the string:"))) {
                println("This element is")
            } else {
                println("This element is missing")
            }
        }
        Commands.REMOVE.ordinal -> {
            if (!trie.remove(stringInput("Enter the string:"))) {
                println("This element is missing")
            }
        }
        Commands.SIZE.ordinal -> println("Size:\n${trie.size()}")
        Commands.HOW_MANY_START_WITH_PREFIX.ordinal -> {
            println("How many start with prefix:\n${trie.howManyStartWithPrefix("Enter a prefix:")}")
        }
        else -> throw IllegalArgumentException("Invalid command")
    }
}

fun main() {
    var command: Int? = -1
    val trie = Trie(Node())
    while (command != 0) {
        displayHints()
        command = readLine()?.toIntOrNull() ?: throw IllegalArgumentException("This is not an integer")
        if (command != 0) {
            executeCommand(command, trie)
        }
    }
}
