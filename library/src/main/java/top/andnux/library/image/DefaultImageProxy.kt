package top.andnux.library.image

import android.widget.ImageView
import com.squareup.picasso.Picasso

class DefaultImageProxy : IImageProxy {

    override fun display(view: ImageView, url: String, defRes: Int) {
        if (url.isEmpty()) {
            view.setImageResource(defRes)
        } else {
            Picasso.get().load(url).into(view)
        }
    }
}