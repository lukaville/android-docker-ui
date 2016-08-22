package ru.lukaville.dockerui.presenter

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.factory
import com.github.salomonbrys.kodein.provider
import ru.lukaville.dockerui.presenter.image.ImageListPresenter
import ru.lukaville.dockerui.presenter.registry.RegistryCreatePresenter
import ru.lukaville.dockerui.presenter.registry.RegistryListPresenter


val presenterModule = Kodein.Module {
    bind<RegistryListPresenter>() with provider {
        RegistryListPresenter(kodein)
    }

    bind<ImageListPresenter>() with factory {
        apiUrl: String -> ImageListPresenter(kodein, apiUrl)
    }

    bind<RegistryCreatePresenter>() with provider {
        RegistryCreatePresenter(kodein)
    }
}