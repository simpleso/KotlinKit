package top.andnux.library.activity

import android.content.Context
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater


abstract class BaseFragment : Fragment() {

    abstract fun getContentViewId(): Int
    protected abstract fun initAllMembersView(savedInstanceState: Bundle?)
    protected var mContext: Context? = null
    protected var mRootView: View? = null

    @Nullable
    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?,
                              @Nullable savedInstanceState: Bundle?): View? {
        mRootView = inflater.inflate(getContentViewId(), container, false)
        this.mContext = activity
        initAllMembersView(savedInstanceState)
        return mRootView
    }

    fun showLoading(msg: String) {
        checkActivityAttached()
        (mContext as BaseActivity<*, *>).showLoading(msg)
    }

    fun hideLoading() {
        checkActivityAttached()
        (mContext as BaseActivity<*, *>).hideLoading()
    }

    fun showToast(msg: String) {
        checkActivityAttached()
        (mContext as BaseActivity<*, *>).showToast(msg)
    }

    fun showErrorText(msg: String) {
        checkActivityAttached()
        (mContext as BaseActivity<*, *>).showErrorText(msg)
    }

    fun showSuccessText(msg: String) {
        checkActivityAttached()
        (mContext as BaseActivity<*, *>).showSuccessText(msg)
    }

    protected fun isAttachedContext(): Boolean {
        return activity != null
    }

    /**
     * 检查activity连接情况
     */
    fun checkActivityAttached() {
        if (activity == null) {
            throw ActivityNotAttachedException()
        }
    }

    class ActivityNotAttachedException : RuntimeException("Fragment has disconnected from Activity ! - -.")

}