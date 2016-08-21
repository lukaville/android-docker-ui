package ru.lukaville.dockerui.presenter

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.provider
import ru.lukaville.dockerui.presenter.registry.RegistryListPresenter


val presenterModule = Kodein.Module {
    bind<RegistryListPresenter>() with provider {
        RegistryListPresenter(kodein)
    }
}