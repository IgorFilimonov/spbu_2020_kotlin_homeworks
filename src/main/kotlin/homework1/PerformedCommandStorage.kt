package homework1

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

/**
 * Stores a set of actions performed on a list of integers and can undo them.
 */
class PerformedCommandStorage {
    var numbers = mutableListOf<Int>()
    private val actions = mutableListOf<Action>()

    /**
     * Called after another program module has performed an action.
     * Adds an [action] to the list of committed for possible cancellation.
     */
    fun performAction(action: Action) {
        actions.add(action)
    }

    /**
     * Removes an [action] from the committed list and calls the method to cancel the action.
     * If no action was taken, displays a message about it.
     */
    fun cancelAction() {
        if (actions.isEmpty()) {
            println("No actions to cancel")
        } else {
            actions.last().cancel(this)
            actions.removeLast()
        }
    }

    fun printNumbers() {
        if (numbers.isEmpty()) {
            error("No numbers to print")
        } else {
            println(numbers.joinToString(" "))
        }
    }

    fun doActionsFromFile(inputFile: String) {
        val actionsFromFile: MutableList<Action> = Json.decodeFromString(File(inputFile).readText())
        actionsFromFile.forEach { it.perform(this) }
    }

    fun writeActionsToFile(outputFile: String) {
        File(outputFile).writeText(Json.encodeToString(actions))
    }
}
