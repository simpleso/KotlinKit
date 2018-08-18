package top.andnux.library.model

import top.andnux.library.extend.convertToString
import java.io.InputStream

open class StringModel(url: String, showLoading: Boolean = true)
    : HttpModel(url, showLoading) {

    abstract class StringCallBack : HttpCallBack() {
        override fun onSuccess(data: InputStream) {
            val string = data.convertToString()
            try {
                onSuccess(string)
            } catch (e: Exception) {
                e.printStackTrace()
                onFail(e.toString())
            }
        }

        abstract fun onSuccess(data: String)
    }
}