package top.andnux.library.image

import android.widget.ImageView

interface IImageProxy {

    fun display(view: ImageView, url: String, defRes: Int = 0)

}