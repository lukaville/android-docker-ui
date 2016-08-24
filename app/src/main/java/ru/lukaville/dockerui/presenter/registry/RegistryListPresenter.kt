package ru.lukaville.dockerui.presenter.registry

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.instance
import ru.lukaville.dockerui.entities.Registry
import ru.lukaville.dockerui.presenter.BasePresenter
import ru.lukaville.dockerui.repository.Storage
import ru.lukaville.dockerui.repository.registry.RegistryRepository
import ru.lukaville.dockerui.ui.DataState
import ru.lukaville.dockerui.ui.Router
import ru.lukaville.dockerui.ui.view.RegistryListView
import rx.lang.kotlin.PublishSubject
import rx.subscriptions.CompositeSubscription

class RegistryListPresenter(override val kodein: Kodein) : BasePresenter<RegistryListView>() {
    var subscription: CompositeSubscription = CompositeSubscription()

    val router = kodein.instance<Router>()
    val registryRepository = kodein.instance<RegistryRepository>(Storage.Database)

    val data = PublishSubject<DataState<MutableList<Registry>>>()

    override fun onCreate() {
        view.registryClicks().subscribe {
            router.detailRegistry(it)
        }

        view.createRegistryClicks().subscribe {
            router.createRegistry()
        }

        view.clearClicks().subscribe {
            registryRepository.clearRegistries()
        }
    }

    override fun onResume() {
        super.onResume()

        subscription = CompositeSubscription()

        val registryListSubscription = view.subscribeRegistryList(data)
        subscription.add(registryListSubscription)

        data.onNext(DataState.Progress())

        val dataSubscription = registryRepository
                .getRegistries()
                .map {
                    if (it.size == 0) {
                        DataState.Empty<MutableList<Registry>>()
                    } else {
                        DataState.Content(it)
                    }
                }
                .subscribe {
                    data.onNext(it)
                }
        subscription.add(dataSubscription)
    }

    override fun onPause() {
        super.onPause()
        subscription.unsubscribe()
    }
}