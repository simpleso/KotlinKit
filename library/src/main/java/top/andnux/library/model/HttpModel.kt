package top.andnux.library.model

import top.andnux.library.http.IHttpCallback
import top.andnux.library.manager.HttpManager
import java.io.File
import java.io.InputStream

open class HttpModel(var url: String, var showLoading: Boolean = true)
    : BaseModel<InputStream>() {

    private var header = HashMap<String, String>()
    private var param = HashMap<String, Any>()

    fun addHeader(key: String, value: String) {
        header[key] = value
    }

    fun removeHeader(key: String) {
        header.remove(key)
    }

    fun clearHeader() {
        header.clear()
    }

    fun addHeaders(values: Map<String, String>) {
        header.putAll(values)
    }

    fun addParam(key: String, value: String) {
        param[key] = value
    }

    fun addParams(values: Map<String, String>) {
        param.putAll(values)
    }

    fun removeParam(key: String) {
        param.remove(key)
    }

    fun clearParam() {
        param.forEach {
            if (it.value is String) {
                param.remove(it.key)
            }
        }
    }

    fun addFile(key: String, value: File) {
        param[key] = value
    }

    fun addFiles(values: Map<String, File>) {
        param.putAll(values)
    }

    fun removeFile(key: String) {
        param.remove(key)
    }

    fun clearFile() {
        param.forEach {
            if (it.value is File) {
                param.remove(it.key)
            }
        }
    }

    override fun fetch(callBack: CallBack<InputStream>?) {
        HttpManager.post(url, showLoading,
                param, header, object : IHttpCallback {

            override fun onSuccess(data: InputStream) {
                callBack?.onSuccess(data)
            }

            override fun onFail(msg: String) {
                super.onFail(msg)
                callBack?.onFail(msg)
            }

            override fun onComplete() {
                super.onComplete()
                callBack?.onComplete()
            }
        })
    }

    open class HttpCallBack : BaseModel.CallBack<InputStream> {
        override fun onSuccess(data: InputStream) {

        }
    }
}