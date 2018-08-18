package top.andnux.library.other

import com.jcodecraeer.xrecyclerview.ProgressStyle
import com.jcodecraeer.xrecyclerview.XRecyclerView

import top.andnux.library.manager.HttpManager
import top.andnux.library.http.StringCallback

abstract class BaseScroller(var mRecyclerView: XRecyclerView,
                            var url: String,
                            var datas: List<Map<String, Any>>)
    : XRecyclerView.LoadingListener {

    var scrollerLoadingListener: ScrollerLoadingListener? = null
    var scrollerDelegate: ScrollerDelegate? = null

    protected var currentPage = 0
    protected var startPage = 1
    protected var totalPage = 0
    protected var pageSize = 10

    init {
        this.mRecyclerView.setPullRefreshEnabled(true)
        this.mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallBeat)
        this.mRecyclerView.setLoadingMoreEnabled(true)
        this.mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallBeat)
        this.mRecyclerView.setLoadingListener(this)
    }

    fun loadData() {
        val map = this.generateParameter()
        var parameter = this.scrollerDelegate?.makeParameter(mRecyclerView)
        HttpManager.post(url, true, parameter, null,
                object : StringCallback() {
                    override fun onSuccess(data: String) {
                        processingResults(data)
                    }

                    override fun onFail(msg: String) {
                        processingException(RuntimeException(msg))
                    }
                })
    }


    fun reloadDatas() {
        this.onRefresh()
    }

    override fun onRefresh() {
        this.currentPage = this.startPage
        if (this.scrollerLoadingListener != null) {
            this.scrollerLoadingListener!!.onRefresh()
        }
        loadData()
    }

    override fun onLoadMore() {
        this.currentPage += 1
        if (this.scrollerLoadingListener != null) {
            this.scrollerLoadingListener!!.onLoadMore()
        }
        loadData()
    }

    protected abstract fun generateParameter(): Map<String, Any>

    protected fun processingException(e: Throwable) {}

    protected abstract fun processingResults(data: String)


    interface ScrollerLoadingListener {

        fun onRefresh()

        fun onLoadMore()
    }

    interface ScrollerDelegate {

        fun makeParameter(xRecyclerView: XRecyclerView): Map<String, Any> {
            return HashMap()
        }
    }
}
