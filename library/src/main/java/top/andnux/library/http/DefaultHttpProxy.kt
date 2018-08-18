package top.andnux.library.http

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import top.andnux.library.extend.closeQuietly
import top.andnux.library.extend.toGetString
import java.io.*
import java.net.HttpURLConnection
import java.net.URL

class DefaultHttpProxy : IHttpProxy {

    override fun get(url: String,
                     showLoading: Boolean,
                     parameter: Map<String, Any>?,
                     header: Map<String, String>?,
                     callback: IHttpCallback?) {
        doAsync {
            var tmpUrl = url
            if (parameter != null) {
                tmpUrl += parameter.toGetString()
            }
            val connection = URL(tmpUrl).openConnection() as HttpURLConnection
            connection.doInput = true
            connection.doOutput = true
            connection.requestMethod = "GET"
            connection.useCaches = false
            header?.forEach {
                connection.setRequestProperty(it.key, it.value)
            }
            if (connection.responseCode == 200) {
                uiThread {
                    callback?.onSuccess(connection.inputStream)
                }
            } else {
                uiThread {
                    callback?.onFail(connection.expiration.toString())
                }
            }
        }
    }

    override fun post(url: String,
                      showLoading: Boolean,
                      parameter: Map<String, Any>?,
                      header: Map<String, String>?,
                      callback: IHttpCallback?) {
        doAsync {
            var tmpUrl = url
            val connection = URL(tmpUrl).openConnection() as HttpURLConnection
            connection.doInput = true
            connection.doOutput = true
            connection.requestMethod = "POST"
            connection.useCaches = false
            header?.forEach {
                connection.setRequestProperty(it.key, it.value)
            }
            var outputStream = connection.outputStream
            var tmpp = ""
            if (parameter != null) {
                tmpp = parameter.toGetString()
            }
            outputStream.write(tmpp.toByteArray())
            outputStream.flush()
            if (connection.responseCode == 200) {
                uiThread {
                    callback?.onSuccess(connection.inputStream)
                }
            } else {
                uiThread {
                    callback?.onFail(connection.expiration.toString())
                }
            }
        }
    }

    override fun upload(url: String,
                        showLoading: Boolean,
                        parameter: Map<String, File>?,
                        header: Map<String, String>?,
                        callback: IHttpCallback?) {
        doAsync {
            val end = "\r\n"
            val twoHyphens = "--"
            val boundary = "*****"
            var ds: DataOutputStream? = null
            var inputStream: InputStream? = null
            var inputStreamReader: InputStreamReader? = null
            var reader: BufferedReader? = null
            val resultBuffer = StringBuffer()
            var tempLine = ""
            try {
                var connection = URL(url).openConnection() as HttpURLConnection
                connection.doOutput = true
                connection.doInput = true
                connection.useCaches = false
                connection.requestMethod = "POST"
                connection.setRequestProperty("Connection", "Keep-Alive")
                connection.setRequestProperty("Charset", "UTF-8")
                connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=$boundary")
                ds = DataOutputStream(connection.outputStream)
                parameter?.forEach {
                    var path = it.value.absolutePath
                    var fileName = path.substring(path.lastIndexOf("/") + 1)
                    ds.writeBytes("$twoHyphens$boundary$end")
                    ds.writeBytes("Content-Disposition: form-data; " +
                            "name=\"${it.key}\";filename=\"$fileName\";$end")
                    ds.writeBytes(end)
                    val fin = FileInputStream(it.value)
                    val buffer = ByteArray(1024)
                    var len = fin.read(buffer)
                    while (len != -1){
                        ds.write(buffer,0,len)
                        len = fin.read(buffer)
                    }
                    ds.writeBytes(end)
                    fin.closeQuietly()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                inputStream?.closeQuietly()
                inputStreamReader?.closeQuietly()
                reader?.closeQuietly()
                ds?.closeQuietly()
            }
        }
    }

    override fun download(url: String,
                          dirs: String,
                          callback: IHttpCallback?) {
        doAsync {
            val connection = URL(url).openConnection() as HttpURLConnection
            if (connection.responseCode == 200) {
                uiThread {
                    callback?.setUrl(url)
                    if (callback is FileCallback) {
                        callback.setDirs(dirs)
                    }
                    callback?.onSuccess(connection.inputStream)
                }
            } else {
                uiThread {
                    callback?.onFail("")
                }
            }
        }
    }
}