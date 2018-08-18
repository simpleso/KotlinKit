package top.andnux.library.model

import com.alibaba.fastjson.JSON

class MapModel(url: String, showLoading: Boolean = true)
    : HttpModel(url, showLoading) {

    abstract class MapCallBack : StringModel.StringCallBack() {
        override fun onSuccess(data: String) {
            try {
                val map = JSON.parseObject(data,
                        Map::class.java) as Map<String, Any>
                onSuccess(map)
            } catch (e: Exception) {
                e.printStackTrace()
                onFail(e.toString())
            }
        }
        abstract fun onSuccess(data: Map<String, Any>)
    }
}