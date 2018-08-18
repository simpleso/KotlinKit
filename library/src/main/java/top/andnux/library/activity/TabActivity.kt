package top.andnux.library.activity

import android.content.res.ColorStateList
import android.graphics.drawable.StateListDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.RadioButton
import kotlinx.android.synthetic.main.a_tab.*
import top.andnux.library.R
import top.andnux.library.adapter.FragmentAdapter
import top.andnux.library.bean.TabBean
import top.andnux.library.extend.intValue
import top.andnux.library.model.TabModel
import top.andnux.library.presenter.TabPresenter
import top.andnux.library.view.TabView

open class TabActivity :
        BaseActivity<TabView, TabModel, TabPresenter>(), TabView {

    private var list: ArrayList<Fragment> = ArrayList()
    private var adapter: FragmentAdapter? = null

    override fun chageTab(index: Int) {
        content.currentItem = index
    }

    override fun setTabs(tabs: List<TabBean>) {
        tabBar.removeAllViews()
        tabs.forEachIndexed { index, it ->
            val item = RadioButton(this)
            item.tag = index
            item.buttonDrawable = null
            val stateImageList = StateListDrawable()
            stateImageList.addState(IntArray(android.R.attr.state_checked),
                    resources.getDrawable(it.selectImageRes))
            stateImageList.addState(IntArray(android.R.attr.state_empty),
                    resources.getDrawable(it.normalImageRes))
            stateImageList.setBounds(5, 5, 5, 5)
            val colors = intArrayOf(it.selectTextColorRes, it.normalTextColorRes)
            val states = arrayOfNulls<IntArray>(2)
            states[0] = intArrayOf(android.R.attr.state_checked)
            states[1] = intArrayOf()
            val colorStateList = ColorStateList(states, colors)
            item.setTextColor(colorStateList)
            item.setText(it.titleRes)
            val layoutParams = LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f)
            layoutParams.gravity = Gravity.CENTER
            tabBar.addView(item, layoutParams)
        }
    }

    override fun makePresenter(): TabPresenter {
        return TabPresenter()
    }

    override fun makeLayouId(): Int {
        return R.layout.a_tab
    }

    override fun initViews(savedInstanceState: Bundle?) {
        adapter = FragmentAdapter(supportFragmentManager, list)
        tabBar.setOnCheckedChangeListener { group, checkedId ->
            val btn: RadioButton = group.findViewById(checkedId)
            presenter.chageTab(btn.tag.intValue())
        }
        presenter.fetch()
    }
}