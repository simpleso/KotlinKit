package top.andnux.library.presenter

import top.andnux.library.model.BaseModel
import top.andnux.library.model.HttpModel
import top.andnux.library.view.BaseView
import java.io.InputStream

open abstract class HttpPresenter<V : BaseView, M : HttpModel>(var url: String)
    : BasePresenter<V, M>() {

    fun fetch(callBack: BaseModel.CallBack<InputStream>?) {
        if (model == null) {
            model = makeModel()
        }
        model?.fetch(callBack)
    }
}