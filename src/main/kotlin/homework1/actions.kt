package homework1

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

/**
 * Sealed class for classes that implement actions on storage.
 */
@Serializable
sealed class Action {
    abstract fun perform(storage: PerformedCommandStorage)
    abstract fun cancel(storage: PerformedCommandStorage)
}

/**
 * Adds an element to the beginning of the storage.
 * @param value Added number.
 */
@Serializable
@SerialName("addToBeginning")
class AddToBeginning(private val value: Int) : Action() {
    override fun perform(storage: PerformedCommandStorage) {
        storage.numbers.add(0, value)
        storage.performAction(this)
    }

    override fun cancel(storage: PerformedCommandStorage) {
        storage.numbers.removeFirst()
    }
}

/**
 * Adds an element to the end of the storage.
 * @param value Added number.
 */
@Serializable
@SerialName("addToEnd")
class AddToEnd(private val value: Int) : Action() {
    override fun perform(storage: PerformedCommandStorage) {
        storage.numbers.add(value)
        storage.performAction(this)
    }

    override fun cancel(storage: PerformedCommandStorage) {
        storage.numbers.removeLast()
    }
}

/**
 * Moves an element from position [from] to position [to].
 */
@Serializable
@SerialName("move")
class Move(private val from: Int, private val to: Int) : Action() {
    /**
     * Simplifies basic methods.
     * @param startIndex The index the item is currently at.
     * @param endIndex The index to which the item should be moved.
     * @param numbers List of numbers from storage, on which actions are performed.
     */
    private fun moveNumbers(startIndex: Int, endIndex: Int, numbers: MutableList<Int>) {
        if (startIndex !in numbers.indices || endIndex !in numbers.indices) {
            throw ArrayIndexOutOfBoundsException("Invalid indices")
        }
        val element = numbers[startIndex]
        numbers.removeAt(startIndex)
        numbers.add(endIndex, element)
    }

    override fun perform(storage: PerformedCommandStorage) {
        moveNumbers(from, to, storage.numbers)
        storage.performAction(this)
    }

    override fun cancel(storage: PerformedCommandStorage) {
        moveNumbers(to, from, storage.numbers)
    }
}
