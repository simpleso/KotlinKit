package top.andnux.library.presenter

import top.andnux.library.model.BaseModel
import top.andnux.library.view.BaseView

open abstract class BasePresenter<V : BaseView, M : BaseModel<*>> {

    var view: V? = null
    var model: M? = null

    abstract fun makeModel(): M

    fun attach(view: V) {
        this.view = view
    }

    fun destroy() {
        this.view = null
    }

    fun isAttached(): Boolean {
        return this.view != null
    }

    open fun fetch(){

    }
}