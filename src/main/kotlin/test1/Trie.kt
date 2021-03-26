package test1

class Node {
    private val transitions = mutableListOf<Pair<Node, Char>>()
    private var isTerminal = false
    private var countPrefixes = 0

    fun goBySymbol(symbol: Char): Node {
        val crutch = transitions[0].first
        for (transition in transitions) {
            if (transition.second == symbol)
                return transition.first
        }
        return crutch // never will be existed
    }

    fun findTransition(symbol: Char): Boolean {
        for (transition in transitions) {
            if (transition.second == symbol)
                return true
        }
        return false
    }

    fun addTransition(newTransition: Pair<Node, Char>) = transitions.add(newTransition)

    fun isTerminal(): Boolean = isTerminal

    fun changeTerminal(newTerminal: Boolean) {
        isTerminal = newTerminal
    }

    fun increaseNumberOfPrefixes() = ++countPrefixes

    fun getNumberOfPrefixes(): Int = countPrefixes
}

class Trie(private val root: Node) {
    private var size = 0

    fun add(element: String): Boolean {
        var currentNode = root
        for (symbol in element) {
            if (!currentNode.findTransition(symbol)) {
                currentNode.addTransition(Pair(Node(), symbol))
            }
            currentNode.goBySymbol(symbol)
            currentNode.increaseNumberOfPrefixes()
        }
        return if (currentNode.isTerminal()) {
            false
        } else {
            currentNode.changeTerminal(true)
            true
        }
    }

    fun contains(element: String): Boolean {
        var currentNode = root
        for (symbol in element) {
            if (!currentNode.findTransition(symbol))
                return false
            currentNode = currentNode.goBySymbol(symbol)
        }
        return currentNode.isTerminal()
    }

    fun remove(element: String): Boolean {
        var currentNode = root
        for (symbol in element) {
            if (!currentNode.findTransition(symbol))
                return false
            currentNode = currentNode.goBySymbol(symbol)
        }
        return if (currentNode.isTerminal()) {
            currentNode.changeTerminal(false)
            true
        } else {
            false
        }
    }

    fun size(): Int {
        return size
    }

    fun howManyStartWithPrefix(prefix: String): Int {
        var currentNode = root
        for (symbol in prefix) {
            if (!currentNode.findTransition(symbol))
                return 0
            currentNode = currentNode.goBySymbol(symbol)
        }
        return currentNode.getNumberOfPrefixes()
    }
}
