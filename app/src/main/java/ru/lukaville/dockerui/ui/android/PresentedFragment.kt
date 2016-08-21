package ru.lukaville.dockerui.ui.android

import android.os.Bundle
import ru.lukaville.dockerui.presenter.BasePresenter
import ru.lukaville.dockerui.ui.view.BaseView

open class PresentedFragment<T : BaseView> : BaseFragment() {
    private var presenter: BasePresenter<T>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter?.onCreate()
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