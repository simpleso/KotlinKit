package top.andnux.library.extend

import java.io.Closeable

fun Closeable?.closeQuietly() {
    try {
        this?.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}