package ru.lukaville.dockerui.ui.view

import ru.lukaville.dockerui.entities.Registry
import rx.Observable
import rx.Subscription

interface RegistryListView : BaseView {
    fun registryClicks(): Observable<Registry>
    fun createRegistry(): Observable<Unit>
    fun subscribeRegistryList(registries: Observable<MutableList<Registry>>): Subscription
}