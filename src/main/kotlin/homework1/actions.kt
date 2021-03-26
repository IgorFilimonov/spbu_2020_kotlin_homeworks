package homework1

/**
 * Interface for classes that implement actions on storage.
 */
interface Action {
    fun perform()
    fun cancel()
}

/**
 * Adds an element to the beginning of the storage.
 * @param value Added number.
 * @param storage Storage being acted on.
 */
class AddToBeginning(private val value: Int, private val storage: PerformedCommandStorage) : Action {
    override fun perform() {
        storage.numbers.add(0, value)
        storage.performAction(this)
    }

    override fun cancel() {
        storage.numbers.removeFirst()
    }
}

/**
 * Adds an element to the end of the storage.
 * @param value Added number.
 * @param storage Storage being acted on.
 */
class AddToEnd(private val value: Int, private val storage: PerformedCommandStorage) : Action {
    override fun perform() {
        storage.numbers.add(value)
        storage.performAction(this)
    }

    override fun cancel() {
        storage.numbers.removeLast()
    }
}

/**
 * Moves an element from position [from] to position [to].
 * @param storage Storage being acted on.
 */
class Move(private val from: Int, private val to: Int, private val storage: PerformedCommandStorage) : Action {
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

    override fun perform() {
        moveNumbers(from, to, storage.numbers)
        storage.performAction(this)
    }

    override fun cancel() {
        moveNumbers(to, from, storage.numbers)
    }
}
