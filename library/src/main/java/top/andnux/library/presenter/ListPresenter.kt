package top.andnux.library.presenter

import top.andnux.library.model.ListModel
import top.andnux.library.view.ListView

class ListPresenter(url: String) : HttpPresenter<ListView, ListModel>(url) {

    override fun makeModel(): ListModel {
        return ListModel(url)
    }
}