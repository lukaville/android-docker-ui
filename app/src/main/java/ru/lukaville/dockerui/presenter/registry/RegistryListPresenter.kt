package ru.lukaville.dockerui.presenter.registry

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.instance
import ru.lukaville.dockerui.presenter.BasePresenter
import ru.lukaville.dockerui.repository.Storage
import ru.lukaville.dockerui.repository.registry.RegistryRepository
import ru.lukaville.dockerui.ui.android.core.router.Router
import ru.lukaville.dockerui.ui.view.RegistryListView
import rx.subscriptions.CompositeSubscription

class RegistryListPresenter(override val kodein: Kodein) : BasePresenter<RegistryListView>() {
    val subscription: CompositeSubscription = CompositeSubscription()

    val router = kodein.instance<Router>()
    val registryRepository = kodein.instance<RegistryRepository>(Storage.Database)

    override fun onCreate() {
        view.registryClicks().subscribe {
            router.detailRegistry(it)
        }

        view.createRegistry().subscribe {
            router.createRegistry()
        }

        view.clearClicks().subscribe {
            registryRepository.clearRegistries()
        }
    }

    override fun onResume() {
        super.onResume()
        subscription.add(
                view.subscribeRegistryList(registryRepository.getRegistries())
        )
    }

    override fun onPause() {
        super.onPause()
        subscription.unsubscribe()
    }
}