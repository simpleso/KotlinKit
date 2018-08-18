package top.andnux.library.presenter

import top.andnux.library.bean.TabBean
import top.andnux.library.model.BaseModel
import top.andnux.library.model.TabModel
import top.andnux.library.view.TabView

class TabPresenter : BasePresenter<TabView, TabModel>() {

    override fun makeModel(): TabModel {
        return TabModel()
    }

    override fun fetch() {
        model?.fetch(object : BaseModel.CallBack<List<TabBean>> {
            override fun onSuccess(data: List<TabBean>) {
                view?.setTabs(data)
            }

            override fun onFail(msg: String) {

            }

            override fun onComplete() {

            }
        })
    }

    fun chageTab(index: Int) {
        view?.chageTab(index)
    }
}