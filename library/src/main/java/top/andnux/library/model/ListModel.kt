package top.andnux.library.model

import com.alibaba.fastjson.JSON

class ListModel(url: String, showLoading: Boolean = true)
    : StringModel(url, showLoading) {

    abstract class ListCallBack : StringCallBack() {
        override fun onSuccess(data: String) {
            try {
                val map = JSON.parseObject(data,
                        List::class.java) as List<Map<String, Any>>
                onSuccess(map)
            } catch (e: Exception) {
                e.printStackTrace()
                onFail(e.toString())
            }
        }

        abstract fun onSuccess(data: List<Map<String, Any>>)
    }
}