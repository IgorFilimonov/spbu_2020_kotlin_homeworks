package homework4

@Suppress("TooManyFunctions")
class AVLNode<K : Comparable<K>, V>(private val myKey: K, private var myValue: V) : MutableMap.MutableEntry<K, V> {
    private var height = 1
    private var leftChild: AVLNode<K, V>? = null
    private var rightChild: AVLNode<K, V>? = null
    private val balanceFactor
        get() = (rightChild?.height ?: 0) - (leftChild?.height ?: 0)

    override val key: K
        get() = myKey

    override val value: V
        get() = myValue

    override fun setValue(newValue: V): V {
        val oldValue = myValue
        myValue = newValue
        return oldValue
    }

    private fun rotateLeft(): AVLNode<K, V> {
        val pivot = rightChild!!
        rightChild = pivot.leftChild
        pivot.leftChild = this
        updateHeight()
        pivot.updateHeight()
        return pivot
    }

    private fun rotateRight(): AVLNode<K, V> {
        val pivot = leftChild!!
        leftChild = pivot.rightChild
        pivot.rightChild = this
        updateHeight()
        pivot.updateHeight()
        return pivot
    }

    fun balance(): AVLNode<K, V> {
        return when {
            balanceFactor > 1 -> {
                if (rightChild!!.balanceFactor < 0) {
                    rightChild = rightChild!!.rotateRight()
                }
                rotateLeft()
            }
            balanceFactor < -1 -> {
                if (leftChild!!.balanceFactor < 0) {
                    leftChild = leftChild!!.rotateRight()
                }
                rotateRight()
            }
            else -> this
        }
    }

    private fun updateHeight() {
        height = maxOf(leftChild?.height ?: 0, rightChild?.height ?: 0) + 1
    }

    fun putRecursive(key: K, value: V): V? {
        var oldValue: V? = null
        if (this.key == key) {
            oldValue = this.value
            this.myValue = value
        } else if (this.key.compareTo(key) < 0) {
            if (rightChild == null) {
                rightChild = AVLNode(key, value)
            } else {
                oldValue = rightChild?.putRecursive(key, value)
                if (oldValue == null) {
                    rightChild = rightChild?.balance()
                }
            }
        } else {
            if (leftChild == null) {
                leftChild = AVLNode(key, value)
            } else {
                oldValue = leftChild?.putRecursive(key, value)
                if (oldValue == null) {
                    leftChild = leftChild?.balance()
                }
            }
        }

        if (oldValue == null) {
            this.updateHeight()
        }
        return oldValue
    }

    fun getRecursive(key: K): V? {
        return when {
            this.key == key -> value
            this.key.compareTo(key) < 0 -> rightChild?.getRecursive(key)
            else -> leftChild?.getRecursive(key)
        }
    }

    fun containsValueRecursive(value: V): Boolean =
        this.value == value ||
        leftChild?.containsValueRecursive(value) ?: false ||
        rightChild?.containsValueRecursive(value) ?: false

    private fun changeParent(parent: AVLNode<K, V>?, newChild: AVLNode<K, V>?, root: AVLNode<K, V>): AVLNode<K, V>? {
        if (parent == null) {
            return newChild
        }
        if (parent.leftChild == this) {
            parent.leftChild = newChild
        } else {
            parent.rightChild = newChild
        }
        return root
    }

    private fun cutMaximumOfSmallerNodes(parent: AVLNode<K, V>, root: AVLNode<K, V>): AVLNode<K, V> {
        if (rightChild == null) {
            changeParent(parent, leftChild, root)
            return this
        }
        val maximumOfSmallNodes = rightChild?.cutMaximumOfSmallerNodes(this, root)
        rightChild = rightChild?.balance()
        updateHeight()
        return maximumOfSmallNodes!!
    }

    fun removeRecursive(key: K, root: AVLNode<K, V>, parent: AVLNode<K, V>? = null): AVLNode<K, V>? {
        var newRoot: AVLNode<K, V>?
        if (this.key == key) {
            if (rightChild == null && leftChild == null) {
                newRoot = changeParent(parent, null, root)
            } else if (leftChild == null) {
                newRoot = changeParent(parent, rightChild, root)
            } else if (rightChild == null) {
                newRoot = changeParent(parent, leftChild, root)
            } else {
                val maximumOfSmallerNodes = cutMaximumOfSmallerNodes(leftChild!!, root)
                maximumOfSmallerNodes.leftChild = leftChild
                maximumOfSmallerNodes.rightChild = rightChild
                maximumOfSmallerNodes.updateHeight()
                newRoot = changeParent(parent, maximumOfSmallerNodes, root)
            }
        } else {
            if (this.key.compareTo(key) < 0) {
                newRoot = rightChild?.removeRecursive(key, root, this)
                rightChild = rightChild?.balance()
            } else {
                newRoot = leftChild?.removeRecursive(key, root, this)
                leftChild = leftChild?.balance()
            }
            updateHeight()
        }
        return newRoot
    }

    fun getEntries(entries: MutableSet<MutableMap.MutableEntry<K, V>>) {
        entries.add(this)
        leftChild?.getEntries(entries)
        rightChild?.getEntries(entries)
    }
}
