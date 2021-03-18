package homework1.PerformedCommandStorage

interface Action {
    fun perform()
    fun cancel()
}

class AddToBeginning(private val value: Int, private val storage: PerformedCommandStorage): Action {
    override fun perform() {
        storage.numbers.add(0, value)
        storage.performAction(this)
    }

    override fun cancel() {
        storage.numbers.removeFirst()
    }
}

class AddToEnd(private val value: Int, private val storage: PerformedCommandStorage): Action {
    override fun perform() {
        storage.numbers.add(value)
        storage.performAction(this)
    }

    override fun cancel() {
        storage.numbers.removeLast()
    }
}

private fun move(startIndex: Int, endIndex: Int, storage: PerformedCommandStorage) {
    if (startIndex !in storage.numbers.indices || endIndex !in storage.numbers.indices) {
        throw ArrayIndexOutOfBoundsException("Invalid indices")
    }
    val element = storage.numbers[startIndex]
    storage.numbers.removeAt(startIndex)
    storage.numbers.add(endIndex, element)
}

class MoveElement(private val startIndex: Int, private val endIndex: Int, private val storage: PerformedCommandStorage): Action {
    override fun perform() {
        move(startIndex, endIndex, storage)
        storage.performAction(this)
    }

    override fun cancel() {
        move(endIndex, startIndex, storage)
    }
}
