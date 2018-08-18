package top.andnux.library.manager

import android.widget.ImageView
import top.andnux.library.image.DefaultImageProxy
import top.andnux.library.image.IImageProxy

object ImageManager {

    var proxy: IImageProxy? = null

    fun display(view: ImageView, url: String, defRes: Int = 0) {
        if (proxy == null) {
            proxy = DefaultImageProxy()
        }
        proxy?.display(view, url,defRes)
    }
}