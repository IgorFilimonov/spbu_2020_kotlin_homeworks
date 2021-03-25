package homework1

interface Action {
    fun perform()
    fun cancel()
}

class AddToBeginning(private val value: Int, private val storage: PerformedCommandStorage) : Action {
    override fun perform() {
        storage.numbers.add(0, value)
        storage.performAction(this)
    }

    override fun cancel() {
        storage.numbers.removeFirst()
    }
}

class AddToEnd(private val value: Int, private val storage: PerformedCommandStorage) : Action {
    override fun perform() {
        storage.numbers.add(value)
        storage.performAction(this)
    }

    override fun cancel() {
        storage.numbers.removeLast()
    }
}

class Move(private val from: Int, private val to: Int, private val storage: PerformedCommandStorage) : Action {
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
