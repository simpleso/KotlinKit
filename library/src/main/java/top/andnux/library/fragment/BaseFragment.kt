package top.andnux.library.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import top.andnux.library.model.BaseModel
import top.andnux.library.presenter.BasePresenter
import top.andnux.library.view.BaseView

open abstract class BaseFragment<V : BaseView, M : BaseModel<*>, P : BasePresenter<V, M>> : Fragment(), BaseView {

    abstract fun makePresenter(): P

    abstract fun makeLayouId(): Int

    abstract fun initViews(savedInstanceState: Bundle?)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(makeLayouId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(savedInstanceState)
    }

    override fun showSuccess(text: String) {


    }

    override fun showError(text: String) {

    }

    override fun showLoadding(text: String) {

    }

    override fun hideLoading() {

    }

    override fun showErrorPage() {

    }

    override fun showSuccessPage() {

    }
}