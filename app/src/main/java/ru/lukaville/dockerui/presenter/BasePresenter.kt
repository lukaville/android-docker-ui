package ru.lukaville.dockerui.presenter

import com.github.salomonbrys.kodein.KodeinAware
import ru.lukaville.dockerui.ui.view.BaseView

abstract class BasePresenter<T: BaseView> : KodeinAware {
    lateinit var view: T

    open fun onCreate() {
    }

    open fun onPause() {
    }

    open fun onResume() {
    }

    open fun onDestroy() {
    }
}