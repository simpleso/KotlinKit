package top.andnux.library.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import top.andnux.library.model.BaseModel
import top.andnux.library.presenter.BasePresenter
import top.andnux.library.view.BaseView

open abstract class BaseActivity<V : BaseView, M : BaseModel<*>,
        P : BasePresenter<V, M>> : AppCompatActivity(), BaseView {

    abstract fun makePresenter(): P

    abstract fun makeLayouId(): Int

    protected lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var id = makeLayouId()
        setContentView(id)
        this.presenter = makePresenter()
        try {
            this.presenter.attach(this as V)
        } catch (e: Exception) {
            e.printStackTrace()
            RuntimeException("you activity must implements BaseView")
        }
        initViews(savedInstanceState)
    }

    abstract fun initViews(savedInstanceState: Bundle?)

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


    override fun onDestroy() {
        this.presenter.destroy()
        super.onDestroy()
    }
}