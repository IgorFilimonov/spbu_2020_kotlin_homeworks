package homework1

fun findOccurrences(string1: String, string2: String): Int {
    var numberOfOccurrences = 0
    for (i in string1.indices) {
        for (j in string2.indices) {
            if (i + j >= string1.length || string1[i + j] != string2[j]) {
                break
            }
            if (j + 1 == string2.length) {
                ++numberOfOccurrences
            }
        }
    }
    return numberOfOccurrences
}

fun main() {
    println("Enter each string on a separate line:")
    val string1 = readLine() ?: error("It's not a string")
    val string2 = readLine() ?: error("It's not a string")
    println("Number of occurrences: ${findOccurrences(string1, string2)}")
}
