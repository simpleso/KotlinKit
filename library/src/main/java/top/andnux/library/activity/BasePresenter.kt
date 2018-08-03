package top.andnux.library.activity

class BasePresenter<T : BaseView> {

    var view: T? = null

    /**
     * 绑定View
     */
    fun attachView(view: T) {
        this.view = view
    }

    /**
     * 销毁View
     */
    fun detachView() {
        this.view = null
    }


    /**
     * 判断View是否已经绑定
     */
    fun isViewAttached(): Boolean {
        return this.view != null
    }
}