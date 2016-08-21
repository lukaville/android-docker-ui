package ru.lukaville.dockerui.ui.android

import android.os.Bundle
import android.support.v4.app.Fragment
import com.github.salomonbrys.kodein.KodeinInjector
import com.github.salomonbrys.kodein.android.appKodein
import ru.lukaville.dockerui.presenter.BasePresenter
import ru.lukaville.dockerui.ui.view.BaseView

open class BaseFragment<T : BaseView> : Fragment() {
    protected val injector = KodeinInjector()
    private var presenter: BasePresenter<T>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector.inject(appKodein())
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