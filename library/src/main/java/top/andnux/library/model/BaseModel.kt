package top.andnux.library.model

abstract class BaseModel<T> {

    abstract fun fetch(callBack: CallBack<T>?)

    interface CallBack<T> {

        fun onSuccess(data: T)

        fun onFail(msg: String){

        }

        fun onComplete(){

        }
    }
}