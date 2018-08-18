package top.andnux.library.activity

import top.andnux.library.model.HttpModel
import top.andnux.library.presenter.BasePresenter
import top.andnux.library.view.BaseView

abstract class HttpActivity<V:BaseView,M:HttpModel,
        P:BasePresenter<V,M>> : BaseActivity<V, M, P>() {

}