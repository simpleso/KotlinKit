package top.andnux.library.bean

import android.support.v4.app.Fragment

data class TabBean(
        var titleRes: Int,
        var selectImageRes: Int,
        var selectTextColorRes: Int,
        var normalImageRes: Int,
        var normalTextColorRes: Int,
        var fragment: Fragment,
        var isExtrude: Boolean = false
)