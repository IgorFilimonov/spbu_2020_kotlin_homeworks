package retest2

import java.lang.IllegalArgumentException

fun ByteArray.compress(): ByteArray {
    val compressedArray: MutableList<Byte> = mutableListOf()
    var numberOfIdentical: Byte = 1
    for (i in 1 until this.size) {
        if (this[i] != this[i - 1]) {
            compressedArray.add(numberOfIdentical)
            compressedArray.add(this[i - 1])
            numberOfIdentical = 1
        } else {
            numberOfIdentical++
        }
    }
    compressedArray.add(numberOfIdentical)
    compressedArray.add(this.last())

    return compressedArray.toByteArray()
}

fun ByteArray.decompress(): ByteArray {
    if (this.size % 2 != 0) {
        throw IllegalArgumentException("Array size must be an even number")
    }
    val decompressedArray: MutableList<Byte> = mutableListOf()
    for (i in 0 until this.size / 2) {
        if (this[2 * i] <= 0) {
            throw IllegalArgumentException("The byte value denoting quantity must be non-negative")
        }
        repeat(this[2 * i].toInt()) { decompressedArray.add(this[2 * i + 1]) }
    }
    return decompressedArray.toByteArray()
}
