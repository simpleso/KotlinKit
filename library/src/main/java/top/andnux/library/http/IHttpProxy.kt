package top.andnux.library.http

import java.io.File

interface IHttpProxy {

    fun get(url: String,
            showLoading: Boolean = true,
            parameter: Map<String, Any>? = null,
            header: Map<String, String>? = null,
            callback: IHttpCallback? = null
    )

    fun post(url: String,
             showLoading: Boolean = true,
             parameter: Map<String, Any>? = null,
             header: Map<String, String>? = null,
             callback: IHttpCallback? = null
    )

    fun upload(url: String,
               showLoading: Boolean = true,
               parameter: Map<String, File>? = null,
               header: Map<String, String>? = null,
               callback: IHttpCallback? = null
    )

    fun download(url: String,
                 dirs: String,
                 callback: IHttpCallback? = null)
}