package top.andnux.library.http

import top.andnux.library.extend.writeToFile
import java.io.File
import java.io.InputStream

abstract class FileCallback : IHttpCallback {

    private var url: String? = ""
    private var dirs: String? = null

    override fun setUrl(url: String) {
        super.setUrl(url)
        this.url = url
    }

    fun setDirs(dirs: String): Unit {
        this.dirs = dirs
    }

    override fun onSuccess(data: InputStream) {
        val ext = url?.split(".")?.last() ?: "obj"
        val name = "${System.currentTimeMillis()}.$ext"
        val file = File(dirs, name)
        data.writeToFile(file.absolutePath)
        onSuccess(file.canonicalFile)
    }

    abstract fun onSuccess(data: File)
}