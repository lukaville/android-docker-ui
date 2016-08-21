package ru.lukaville.dockerui.ui.android

import android.os.Bundle
import ru.lukaville.dockerui.presenter.BasePresenter
import ru.lukaville.dockerui.ui.view.BaseView

abstract class PresentedActivity<T: BaseView> : BaseActivity() {
    private var presenter: BasePresenter<T>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.presenter?.onCreate()
    }

    protected fun registerPresenter(presenter: BasePresenter<T>) {
        this.presenter = presenter
        this.presenter?.onCreate()
    }

    override fun onPause() {
        super.onPause()
        presenter?.onPause()
    }

    override fun onResume() {
        super.onResume()
        presenter?.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
    }
}