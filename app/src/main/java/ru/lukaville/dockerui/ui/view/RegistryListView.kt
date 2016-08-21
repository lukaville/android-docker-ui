package ru.lukaville.dockerui.ui.view

import ru.lukaville.dockerui.entities.Registry
import rx.Observable

interface RegistryListView : BaseView {
    fun registryClicks(): Observable<Registry>
    fun createRegistry(): Observable<Unit>
}