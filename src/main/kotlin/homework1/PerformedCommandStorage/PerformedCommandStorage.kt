package homework1.PerformedCommandStorage

class PerformedCommandStorage {
    var numbers = mutableListOf<Int>()
    private val actions = mutableListOf<Action>()

    fun performAction(action: Action) {
        actions.add(action)
    }

    fun cancelAction() {
        if (actions.isEmpty()) {
            println("No actions to cancel")
        } else {
            actions.last().cancel()
            actions.removeLast()
        }
    }

    fun printNumbers() {
        if (numbers.isEmpty()) {
            throw IllegalStateException("No numbers to print")
        } else {
            println(numbers.joinToString(" "))
        }
    }
}
