package homework1

interface Action {
    fun perform()
    fun cancel()
}

class AddToBeginning(private val value: Int?, private val storage: PerformedCommandStorage) : Action {
    init {
        if (value == null) {
            throw IllegalArgumentException("This is not an integer")
        }
    }

    override fun perform() {
        storage.numbers.add(0, value!!.toInt())
        storage.performAction(this)
    }

    override fun cancel() {
        storage.numbers.removeFirst()
    }
}

class AddToEnd(private val value: Int?, private val storage: PerformedCommandStorage) : Action {
    init {
        if (value == null) {
            throw IllegalArgumentException("This is not an integer")
        }
    }

    override fun perform() {
        storage.numbers.add(value!!.toInt())
        storage.performAction(this)
    }

    override fun cancel() {
        storage.numbers.removeLast()
    }
}

private fun move(startIndex: Int, endIndex: Int, storage: PerformedCommandStorage) {
    val element = storage.numbers[startIndex]
    storage.numbers.removeAt(startIndex)
    storage.numbers.add(endIndex, element)
}

class MoveElement() : Action {
    private var startIndex: Int? = null
    private var endIndex: Int? = null
    private lateinit var storage: PerformedCommandStorage
    // The line with the primary constructor was too long

    constructor(startIndex: Int?, endIndex: Int?, storage: PerformedCommandStorage) {
        this.startIndex = startIndex
        this.endIndex = endIndex
        this.storage = storage
    }

    init {
        if (startIndex == null || endIndex == null) {
            throw IllegalArgumentException("This is not an integer")
        }
        if (startIndex !in storage.numbers.indices || endIndex !in storage.numbers.indices) {
            throw ArrayIndexOutOfBoundsException("Invalid indices")
        }
    }

    override fun perform() {
        move(startIndex!!.toInt(), endIndex!!.toInt(), storage)
        storage.performAction(this)
    }

    override fun cancel() {
        move(endIndex!!.toInt(), startIndex!!.toInt(), storage)
    }
}
