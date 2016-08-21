package ru.lukaville.dockerui.ui.view

import ru.lukaville.dockerui.entities.registry.Registry
import rx.Observable


interface RegistryCreateView : BaseView {
    fun createRegistry(): Observable<Registry>
}