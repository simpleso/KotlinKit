package top.andnux.library.activity

interface BaseView {

    /***
     * 显示加载进度框
     */
    fun showLoading(text: String = "加载中")

    /**
     * 隐藏进度框
     */
    fun hideLoading()

    /**
     * 显示错误消息
     */
    fun showErrorText(text: String)

    /**
     * 显示成功消息
     */
    fun showSuccessText(text: String)

    /**
     * 显示普通消息
     */
    fun showToast(text: String)

}