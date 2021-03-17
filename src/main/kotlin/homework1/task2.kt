package homework1

fun String.findOccurrences(sample: String): Int {
    if (this.isEmpty() || sample.isEmpty()) {
        error("Strings must be non-empty")
    }
    var numberOfOccurrences = 0
    var i = 0
    while (i < this.length) {
        val index = this.indexOf(sample, i, false)
        if (index != -1) {
            ++numberOfOccurrences
            i = index + 1
        } else {
            ++i
        }
    }
    return numberOfOccurrences
}

fun main() {
    println("Enter each string on a separate line:")
    val string1 = readLine() ?: error("It's not a string")
    val string2 = readLine() ?: error("It's not a string")
    println("Number of occurrences: ${string1.findOccurrences(string2)}")
}
