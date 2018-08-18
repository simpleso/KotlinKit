package top.andnux.library.extend

fun <T> List<T>?.limit(count: Int): List<T> {
    if (this?.count() ?: 0 < count) {
        return this ?: ArrayList()
    }
    val list = ArrayList<T>()
    for (i in 0 until count) {
        this?.get(i)?.let { list.add(it) }
    }
    return list
}