package homework4

class AVL<K : Comparable<K>, V> : MutableMap<K, V> {
    private var root: AVLNode<K, V>? = null
    private var _size = 0

    override fun clear() {
        root = null
        _size = 0
    }

    override fun containsKey(key: K): Boolean = get(key) != null

    override fun containsValue(value: V): Boolean = root?.containsValueRecursive(value) ?: false

    override val entries: MutableSet<MutableMap.MutableEntry<K, V>>
        get() {
            val tempEntries = mutableSetOf<MutableMap.MutableEntry<K, V>>()
            root?.getEntries(tempEntries)
            return tempEntries
        }

    override fun get(key: K): V? = root?.getRecursive(key)

    override fun isEmpty(): Boolean = _size == 0

    override val keys: MutableSet<K>
        get() {
            val tempKeys = mutableSetOf<K>()
            entries.forEach { tempKeys.add(it.key) }
            return tempKeys
        }

    override fun put(key: K, value: V): V? {
        if (isEmpty()) {
            root = AVLNode(key, value)
            ++_size
            return null
        }

        val oldValue = root?.putRecursive(key, value)
        if (oldValue == null) {
            root = root?.balance()
            ++_size
        }
        return oldValue
    }

    override fun putAll(from: kotlin.collections.Map<out K, V>) {
        from.forEach { put(it.key, it.value) }
    }

    override fun remove(key: K): V? {
        val oldValue = get(key)
        if (oldValue != null) {
            root = root?.removeRecursive(key, root!!)
            root = root?.balance()
            --_size
        }
        return oldValue
    }

    override val size: Int
        get() = _size

    override val values: MutableCollection<V>
        get() {
            val tempValues: MutableCollection<V> = mutableSetOf<V>()
            entries.forEach { tempValues.add(it.value) }
            return tempValues
        }
}
