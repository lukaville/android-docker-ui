package ru.lukaville.dockerui.mock

import ru.lukaville.dockerui.entities.Registry
import ru.lukaville.dockerui.ui.view.RegistryCreateView
import rx.Observable
import rx.lang.kotlin.PublishSubject

class MockRegistryCreateView : RegistryCreateView {
    val registryCreateSubject = PublishSubject<Registry>()

    override fun createRegistry(): Observable<Registry> {
        return registryCreateSubject
    }

    fun createRegistryClick(registry: Registry) {
        registryCreateSubject.onNext(registry)
    }
}
