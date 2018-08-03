package top.andnux.library.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity<V : BaseView, P : BasePresenter<V>> : AppCompatActivity(), BaseView {

    abstract fun getPresenter(): P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun showLoading(text: String) {


    }

    override fun hideLoading() {

    }

    override fun showErrorText(text: String) {

    }

    override fun showSuccessText(text: String) {

    }

    override fun showToast(text: String) {

    }

}