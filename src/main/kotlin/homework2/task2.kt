package homework2

fun readArray(): IntArray {
    println("Enter the size of the array:")
    val size = readLine()?.toIntOrNull()
    if (size == null || size <= 0) {
        throw IllegalArgumentException("Invalid array size")
    }
    val numbers = IntArray(size)

    println("Enter an array of integers:")
    val scanner = java.util.Scanner(System.`in`)
    for (i in numbers.indices) {
        if (!scanner.hasNextInt()) {
            throw IllegalArgumentException("This is not an integer")
        }
        numbers[i] = scanner.nextInt()
    }

    return numbers
}

fun IntArray.removeDuplicates(): IntArray {
    val set = mutableSetOf<Int>()
    set.addAll(this.toTypedArray().reversedArray())
    return set.toIntArray().reversedArray()
}

fun main() {
    var numbers = readArray()
    numbers = numbers.removeDuplicates()
    println("Numbers:\n${numbers.joinToString(" ")}")
}
