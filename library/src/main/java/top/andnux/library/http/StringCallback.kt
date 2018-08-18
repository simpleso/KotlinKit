package top.andnux.library.http

import top.andnux.library.extend.convertToString
import java.io.InputStream

abstract class StringCallback : IHttpCallback {

    override fun onSuccess(data: InputStream) {
        val string = data.convertToString()
        onSuccess(string)
    }

    abstract fun onSuccess(data: String)
}