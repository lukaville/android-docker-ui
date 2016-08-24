package ru.lukaville.dockerui.mock

import ru.lukaville.dockerui.entities.Registry
import ru.lukaville.dockerui.ui.DataState
import ru.lukaville.dockerui.ui.view.RegistryListView
import rx.Observable
import rx.Subscription
import rx.lang.kotlin.PublishSubject

class MockRegistryListView : RegistryListView {
    val registryClicks = PublishSubject<Registry>()
    val createRegistry = PublishSubject<Unit>()
    val clearClicks = PublishSubject<Unit>()

    val dataEvents = mutableListOf<DataState<MutableList<Registry>>>()

    override fun registryClicks(): Observable<Registry> {
        return registryClicks
    }

    fun registryClick(registry: Registry) {
        registryClicks.onNext(registry)
    }

    override fun createRegistryClicks(): Observable<Unit> {
        return createRegistry
    }

    fun createRegistryClick() {
        createRegistry.onNext(Unit)
    }

    override fun clearClicks(): Observable<Unit> {
        return clearClicks
    }

    fun clearRegistriesClick() {
        clearClicks.onNext(Unit)
    }

    fun getLastEvent(): DataState<MutableList<Registry>> {
        val event = dataEvents.last()
        dataEvents.removeAt(dataEvents.size - 1)
        return event
    }

    override fun subscribeRegistryList(data: Observable<DataState<MutableList<Registry>>>): Subscription {
        return data
                .subscribe {
                    dataEvents.add(it)
                }
    }
}
