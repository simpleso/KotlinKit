package top.andnux.library.adapter

import android.content.Context
import android.view.LayoutInflater

import top.andnux.library.adapter.base.ItemViewDelegate
import top.andnux.library.adapter.base.ViewHolder

/**
 * Created by zhy on 16/4/9.
 */
abstract class CommonAdapter<T>(mContext: Context,
                                private var mLayoutId: Int,
                                private var mDatas: List<T>) :
        MultiItemTypeAdapter<T>(mContext, mDatas) {

    init {
        addItemViewDelegate(object : ItemViewDelegate<T> {
            override val itemViewLayoutId: Int
                get() {
                    return mLayoutId
                }

            override fun isForViewType(item: T, position: Int): Boolean {
                return true
            }

            override fun convert(holder: ViewHolder, t: T, position: Int) {
                this@CommonAdapter.convert(holder, t, position)
            }

        })
    }

    protected abstract fun convert(holder: ViewHolder, t: T, position: Int)
}
