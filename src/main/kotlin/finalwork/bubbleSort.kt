package finalwork

fun <T> Iterable<T>.bubbleSort(comparator: Comparator<T>): MutableList<T> {
    val list = this.toMutableList()
    for (i in 0..list.count() - 2) {
        for (j in 0..list.count() - 2) {
            if (comparator.compare(list[j], list[j + 1]) > 0) {
                val temp = list[j]
                list[j] = list[j + 1]
                list[j + 1] = temp
            }
        }
    }

    return list
}
