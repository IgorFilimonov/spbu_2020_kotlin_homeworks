package retest2

class CyclicList<T> {
    private var head: ListNode<T>? = null
    private var size = 0

    fun isEmpty(): Boolean = size == 0

    fun add(data: T, position: Int) {
        if (position < 0 || position > size) {
            throw IndexOutOfBoundsException("Invalid position")
        }
        val newNode = ListNode<T>(data)
        if (position == 0) {
            newNode.next = head
            head = newNode
        } else {
            var previousOfAdding = head
            repeat(position - 1) { previousOfAdding = previousOfAdding?.next }
            newNode.next = previousOfAdding?.next?.next
            previousOfAdding?.next = newNode
        }
        if (position == size) {
            newNode.next = head
        }
        size++
    }

    fun remove(position: Int) {
        if (position < 0 || position >= size) {
            throw IndexOutOfBoundsException("Invalid position")
        }
        if (position == 0) {
            head = if (size == 1) null else head?.next
        } else {
            var previousOfRemovable = head
            repeat(position - 1) { previousOfRemovable = previousOfRemovable?.next }
            previousOfRemovable?.next = previousOfRemovable?.next?.next
        }
        size--
    }

    fun get(position: Int): T = jump(position, 0)

    fun jump(position: Int, step: Int): T {
        if (position < 0 || position >= size) {
            throw IndexOutOfBoundsException("Invalid position")
        }
        var currentNode = head
        repeat(position + step) { currentNode = currentNode?.next }
        return currentNode?.data ?: throw NullPointerException("Null is here")
    }

    fun getRepresentationForTest(): MutableList<T?> {
        val list: MutableList<T?> = mutableListOf()
        var currentNode = head
        repeat(size) {
            list.add(currentNode?.data)
            currentNode = currentNode?.next
        }
        return list
    }
}

class ListNode<T>(val data: T) {
    var next: ListNode<T>? = null
}
