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
            val _entries = mutableSetOf<MutableMap.MutableEntry<K, V>>()
            root?.getEntries(_entries)
            return _entries
        }

    override fun get(key: K): V? = root?.getRecursive(key)

    override fun isEmpty(): Boolean = _size == 0

    override val keys: MutableSet<K>
        get() {
            val _keys = mutableSetOf<K>()
            entries.forEach { _keys.add(it.key) }
            return _keys
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
            val _values: MutableCollection<V> = mutableSetOf<V>()
            entries.forEach { _values.add(it.value) }
            return _values
        }
}
