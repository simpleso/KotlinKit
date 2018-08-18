package top.andnux.library.http

import com.alibaba.fastjson.JSON
import java.lang.reflect.ParameterizedType

abstract class JsonCallBack<T> : StringCallback() {

    override fun onSuccess(data: String) {
        val pType = this.javaClass.genericSuperclass
                as ParameterizedType
        val type = pType.actualTypeArguments[0]
        val obj = JSON.parseObject<T>(data, type)
        onSuccess(obj)
    }

    abstract fun onSuccess(data: T)
}