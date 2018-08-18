package top.andnux.library.view

interface BaseView {

    /**
     * 显示成功消息
     */
    fun showSuccess(text: String = "")

    /**
     * 显示错误消息
     */
    fun showError(text: String = "")

    /**
     * 显示加载中。。。
     */
    fun showLoadding(text: String = "")

    /**
     * 隐藏加载中。。。
     */
    fun hideLoading()

    /**
     * 显示错误页面
     */
    fun showErrorPage()

    /**
     * 显示成功
     */
    fun showSuccessPage()
}