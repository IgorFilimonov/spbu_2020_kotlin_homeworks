package homework1

/**
 * A storage of numbers that stores a set of committed actions and is able to undo them
 */
class PerformedCommandStorage {
    var numbers = mutableListOf<Int>()
    private val actions = mutableListOf<Action>()

    /**
     * A function to be called after an action has been executed by another program module
     * Adds an [action] to the list of committed for possible cancellation
     */
    fun performAction(action: Action) {
        actions.add(action)
    }

    /**
     * Removes an [action] from the committed list and calls the method to cancel the action
     * If no action was taken, displays a message about it
     */
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
