package top.andnux.library.view

import top.andnux.library.bean.TabBean

interface TabView : BaseView {

    fun chageTab(index: Int)

    fun setTabs(tabs: List<TabBean>)

}