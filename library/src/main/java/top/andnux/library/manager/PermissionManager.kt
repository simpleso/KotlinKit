package top.andnux.library.manager

import android.Manifest
import android.support.v4.app.FragmentActivity
import android.text.TextUtils
import android.widget.Toast

import com.tbruyelle.rxpermissions2.RxPermissions


object PermissionManager {

    //权限组，用户授权了某个权限，同一个权限组里面的其他权限也会自动授予权限。
    //这里每组选择一个权限，如果这个规则改变了，就要授权具体的权限。
    val CALENDAR = "android.permission.READ_CALENDAR" //日历
    val CAMERA = "android.permission.CAMERA" //相机
    val CONTACTS = "android.permission.READ_CONTACTS" //通讯录
    val LOCATION = "android.permission.ACCESS_FINE_LOCATION" //定位
    val RECORD_AUDIO = "android.permission.RECORD_AUDIO" //麦克风
    val PHONE = "android.permission.CALL_PHONE" //电话
    val SENSORS = "android.permission.BODY_SENSORS" //传感器
    val SMS = "android.permission.READ_SMS" //短信
    val STORAGE = "android.permission.WRITE_EXTERNAL_STORAGE" //存储

    //用户禁止授权的默认提示语
    private val SHOW_AGAIN_MESSAGE = "您拒绝了授权，无法正常使用"
    //用户禁止授权并且勾选了不再询问的默认提示语
    private val NOT_SHOW_AGAIN_MESSAGE = "您禁止了授权，请在手机设置里面授权"
    private var createAPPDir = false //是否创建APP的文件目录

    /**
     * 请求危险权限的授权
     *
     * @param activity
     * @param listener
     * @param args
     */
    fun requestEach(activity: FragmentActivity,
                    listener: OnPermissionListener,
                    vararg args: String) {
        requestEach(activity, SHOW_AGAIN_MESSAGE,
                NOT_SHOW_AGAIN_MESSAGE, listener, *args)
    }

    /**
     * 请求危险权限的授权
     *
     * @param activity
     * @param showAgainMsg    用户禁止授权的提示语
     * @param notShowAgainMsg 用户禁止授权并且勾选了不再询问的提示语
     * @param listener
     * @param args            权限组
     */
    fun requestEach(activity: FragmentActivity,
                    showAgainMsg: String,
                    notShowAgainMsg: String,
                    listener: OnPermissionListener,
                    vararg args: String) {

        if (args.size == 1) {
            //只请求一个权限
            requestSingleEach(activity, showAgainMsg, notShowAgainMsg, listener, args[0])
        } else if (args.size > 1) {
            //请求多个权限
            requestMultipleEach(activity, showAgainMsg, listener, *args)
        }
    }

    /**
     * 请求单个授权
     *
     * @param activity
     * @param showAgainMsg    用户禁止授权的提示语
     * @param notShowAgainMsg 用户禁止授权并且勾选了不再询问的提示语
     * @param listener
     * @param permission      权限
     */
    private fun requestSingleEach(activity: FragmentActivity,
                                  showAgainMsg: String,
                                  notShowAgainMsg: String,
                                  listener: OnPermissionListener?,
                                  permission: String) {
        val rxPermissions = RxPermissions(activity)
        rxPermissions.requestEach(permission)
                .subscribe { permission1 ->
                    if (permission1.granted) {
                        //授权成功
                        listener?.onSucceed()
                    } else {
                        if (permission1.shouldShowRequestPermissionRationale) {
                            //拒绝后允许再次提示
                            Toast.makeText(activity, if (!TextUtils.isEmpty(showAgainMsg))
                                showAgainMsg
                            else
                                SHOW_AGAIN_MESSAGE, Toast.LENGTH_SHORT).show()
                        } else if (!permission1.shouldShowRequestPermissionRationale) {
                            //拒绝后不再提示
                            Toast.makeText(activity, if (!TextUtils.isEmpty(notShowAgainMsg))
                                notShowAgainMsg
                            else
                                NOT_SHOW_AGAIN_MESSAGE, Toast.LENGTH_SHORT).show()
                        }
                        listener?.onFailed(permission1.shouldShowRequestPermissionRationale)
                    }

                }
    }

    /**
     * 同时请求多个授权
     *
     * @param activity
     * @param showMsg  没有全部授权的提示语
     * @param listener
     * @param args     权限组
     */
    private fun requestMultipleEach(activity: FragmentActivity,
                                    showMsg: String,
                                    listener: OnPermissionListener?,
                                    vararg args: String) {

        //判断是否创建APP文件目录，获取存储权限的时候创建
        createAPPDir = false
        for (arg in args) {
            if (arg == Manifest.permission.WRITE_EXTERNAL_STORAGE ||
                    arg == Manifest.permission.READ_EXTERNAL_STORAGE) {
                createAPPDir = true
                break
            }
        }

        val rxPermissions = RxPermissions(activity)
        rxPermissions.request(*args)
                .subscribe { aBoolean ->
                    if (aBoolean) {
                        //全部已经授权
                        listener?.onSucceed()

                    } else {
                        //起码有一个没有授权
                        listener?.onFailed(true)
                        //显示拒绝授权提示
                        Toast.makeText(activity, if (!TextUtils.isEmpty(showMsg))
                            showMsg
                        else
                            SHOW_AGAIN_MESSAGE, Toast.LENGTH_SHORT).show()
                    }
                }
    }

    interface OnPermissionListener {

        fun onSucceed()

        fun onFailed(showAgain: Boolean)
    }

}
