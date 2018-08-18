package top.andnux.library.fragment

import android.os.Bundle
import kotlinx.android.synthetic.main.a_list.*
import top.andnux.library.R
import top.andnux.library.model.ListModel
import top.andnux.library.other.BaseScroller
import top.andnux.library.presenter.ListPresenter
import top.andnux.library.view.ListView

abstract class ListFragment : BaseFragment<ListView, ListModel, ListPresenter>() {

    var scroll: BaseScroller? = null
    var datas: List<Map<String, Any>> = ArrayList()
    abstract fun getUrl(): String

    override fun makePresenter(): ListPresenter {
        return ListPresenter("")
    }

    override fun makeLayouId(): Int {
        return R.layout.f_list
    }

    override fun initViews(savedInstanceState: Bundle?) {
        scroll = object : BaseScroller(xList, getUrl(), datas) {
            override fun generateParameter(): Map<String, Any> {
                return HashMap()
            }

            override fun processingResults(data: String) {

            }
        }
    }
}