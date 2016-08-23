package ru.lukaville.dockerui.presenter

import android.util.Log
import com.github.salomonbrys.kodein.KodeinAware
import ru.lukaville.dockerui.ui.view.BaseView

abstract class BasePresenter<T: BaseView> : KodeinAware {
    lateinit var view: T

    open fun onCreate() {
        Log.d("Presenter", "onCreate")
    }

    open fun onPause() {
        Log.d("Presenter", "onPause")
    }

    open fun onResume() {
        Log.d("Presenter", "onResume")
    }

    open fun onDestroy() {
        Log.d("Presenter", "onDestroy")
    }
}