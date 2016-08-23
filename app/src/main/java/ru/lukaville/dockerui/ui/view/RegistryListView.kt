package ru.lukaville.dockerui.ui.view

import ru.lukaville.dockerui.entities.Registry
import ru.lukaville.dockerui.ui.DataState
import rx.Observable
import rx.Subscription

interface RegistryListView : BaseView {
    fun registryClicks(): Observable<Registry>
    fun createRegistry(): Observable<Unit>
    fun clearClicks(): Observable<Unit>
    fun subscribeRegistryList(data: Observable<DataState<MutableList<Registry>>>): Subscription
}