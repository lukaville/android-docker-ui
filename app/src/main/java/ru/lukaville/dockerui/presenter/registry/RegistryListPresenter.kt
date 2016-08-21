package ru.lukaville.dockerui.presenter.registry

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.instance
import ru.lukaville.dockerui.entities.registry.Registry
import ru.lukaville.dockerui.presenter.BasePresenter
import ru.lukaville.dockerui.ui.android.Router
import ru.lukaville.dockerui.ui.view.RegistryListView

class RegistryListPresenter(override val kodein: Kodein) : BasePresenter<RegistryListView>() {
    val router = kodein.instance<Router>()

    override fun onCreate() {
        view.registryClicks().subscribe {
            this.openRegistryDetails(it)
        }
    }

    fun openRegistryDetails(registry: Registry) {
        router.detailRegistry(registry)
    }
}