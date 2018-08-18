package top.andnux.library.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.a_list.*
import top.andnux.library.R
import top.andnux.library.adapter.CommonAdapter
import top.andnux.library.adapter.base.ViewHolder
import top.andnux.library.model.ListModel
import top.andnux.library.presenter.ListPresenter
import top.andnux.library.view.ListView

open abstract class ListActivity : BaseActivity<ListView, ListModel, ListPresenter>(), ListView {

    var datas: List<Map<String, Any>> = ArrayList()

    protected fun makeLayouManager(): RecyclerView.LayoutManager {
        var manager = LinearLayoutManager(this)
        manager.orientation = LinearLayoutManager.VERTICAL
        return manager
    }

    protected abstract fun getLayouID(): Int

    protected abstract fun convertViewHolder(holder: ViewHolder, t: Map<String, Any>, position: Int)

    protected fun makeAdapter(): CommonAdapter<Map<String, Any>> {
        return object : CommonAdapter<Map<String, Any>>(this, getLayouID(), datas) {
            override fun convert(holder: ViewHolder, t: Map<String, Any>, position: Int) {
                convertViewHolder(holder, t, position)
            }
        }
    }

    override fun makePresenter(): ListPresenter {
        return ListPresenter("")
    }

    override fun makeLayouId(): Int {
        return R.layout.a_list
    }

    override fun initViews(savedInstanceState: Bundle?) {
        xList.layoutManager = makeLayouManager()
        xList.adapter = makeAdapter()
        presenter.fetch()
    }
}