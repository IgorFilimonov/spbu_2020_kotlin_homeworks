package retest1

class List<T> {
    private var head: ListNode<T>? = null
    private var tail: ListNode<T>? = null
    private var size = 0

    fun isEmpty(): Boolean = size == 0

    fun add(data: T) {
        if (isEmpty()) {
            head = ListNode<T>(data)
            tail = head
        } else {
            val newNode = ListNode<T>(data)
            tail?.next = newNode
            newNode.previous = tail
            tail = newNode
        }
        size++
    }

    fun add(data: T, position: Int) {
        if (position < 0 || position > size) {
            throw IndexOutOfBoundsException("Invalid position")
        }
        val newNode = ListNode<T>(data)
        if (position == 0) {
            head = newNode
        }
        if (position == size) {
            tail?.next = newNode
            newNode.previous = tail
            tail = newNode
        } else {
            var currentNode = head
            repeat(position) { currentNode = currentNode?.next }
            currentNode?.previous?.next = newNode
            newNode.previous = currentNode?.previous
            newNode.next = currentNode
            currentNode?.previous = newNode
        }
        size++
    }

    fun remove(position: Int) {
        if (position < 0 || position >= size) {
            throw IndexOutOfBoundsException("Invalid position")
        }
        if (position == 0) {
            head = head?.next
            if (head == null) {
                tail = null
            } else {
                head?.previous = null
            }
        } else {
            var previousOfRemovable = head
            repeat(position - 1) { previousOfRemovable = previousOfRemovable?.next }
            var nextOfRemovable = previousOfRemovable?.next?.next
            if (previousOfRemovable?.next == tail) {
                tail = previousOfRemovable
            }
            previousOfRemovable?.next = nextOfRemovable
            nextOfRemovable?.previous = previousOfRemovable
            size--
        }
    }

    fun get(): T = head?.data ?: throw IllegalStateException("List is empty")

    fun get(position: Int): T {
        if (position < 0 || position > size) {
            throw IndexOutOfBoundsException("Invalid position")
        }
        var currentNode = head
        repeat(position) { currentNode = currentNode?.next }
        return currentNode?.data ?: throw IndexOutOfBoundsException("Invalid position")
    }
}

class ListNode<T>(val data: T) {
    var next: ListNode<T>? = null
    var previous: ListNode<T>? = null
}
