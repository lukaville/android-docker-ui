package ru.lukaville.dockerui.presenter.registry

import android.util.Log
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.instance
import ru.lukaville.dockerui.entities.Registry
import ru.lukaville.dockerui.presenter.BasePresenter
import ru.lukaville.dockerui.repository.Storage
import ru.lukaville.dockerui.repository.registry.RegistryRepository
import ru.lukaville.dockerui.ui.DataState
import ru.lukaville.dockerui.ui.android.core.router.Router
import ru.lukaville.dockerui.ui.view.RegistryListView
import rx.lang.kotlin.PublishSubject
import rx.subscriptions.CompositeSubscription

class RegistryListPresenter(override val kodein: Kodein) : BasePresenter<RegistryListView>() {
    val subscription: CompositeSubscription = CompositeSubscription()

    val router = kodein.instance<Router>()
    val registryRepository = kodein.instance<RegistryRepository>(Storage.Database)

    val data = PublishSubject<DataState<MutableList<Registry>>>()

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
                view.subscribeRegistryList(data)
        )

        data.onNext(
                DataState.Progress()
        )

        subscription.add(
                registryRepository
                        .getRegistries()
                        .map {
                            if (it.size == 0) {
                                Log.d("RegistryListPresenter", "New registries: Empty")
                                DataState.Empty<MutableList<Registry>>()
                            } else {
                                Log.d("RegistryListPresenter", "New registries: Content")
                                DataState.Content(it)
                            }
                        }
                        .subscribe(data)
        )
    }

    override fun onPause() {
        super.onPause()
        subscription.unsubscribe()
    }
}