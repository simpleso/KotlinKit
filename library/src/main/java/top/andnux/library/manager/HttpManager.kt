package top.andnux.library.manager

import top.andnux.library.http.DefaultHttpProxy
import top.andnux.library.http.FileCallback
import top.andnux.library.http.IHttpCallback
import top.andnux.library.http.IHttpProxy
import java.io.File

object HttpManager {

    var proxy: IHttpProxy? = null

    fun  get(url: String,
                showLoading: Boolean,
                parameter: Map<String, Any>?  = null,
                header: Map<String, String>?  = null,
                callback: IHttpCallback?  = null) {
        if (proxy == null) {
            proxy = DefaultHttpProxy()
        }
        proxy?.get(url, showLoading, parameter, header, callback)
    }

    fun post(url: String,
                 showLoading: Boolean,
                 parameter: Map<String, Any>? = null,
                 header: Map<String, String>? = null,
                 callback: IHttpCallback?  = null) {
        if (proxy == null) {
            proxy = DefaultHttpProxy()
        }
        proxy?.post(url, showLoading, parameter, header, callback)
    }

    fun upload(url: String,
                   showLoading: Boolean = true,
                   parameter: Map<String, File>? = null,
                   header: Map<String, String>? = null,
                   callback: IHttpCallback? = null) {
        if (proxy == null) {
            proxy = DefaultHttpProxy()
        }
        proxy?.upload(url, showLoading, parameter, header, callback)
    }

    fun download(url: String,
                 dirs: String,
                 callback: FileCallback? = null) {
        if (proxy == null) {
            proxy = DefaultHttpProxy()
        }
        proxy?.download(url, dirs, callback)
    }
}