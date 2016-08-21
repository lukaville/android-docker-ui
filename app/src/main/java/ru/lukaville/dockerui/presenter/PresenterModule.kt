package ru.lukaville.dockerui.presenter

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.provider
import ru.lukaville.dockerui.presenter.registry.RegistryCreatePresenter
import ru.lukaville.dockerui.presenter.registry.RegistryListPresenter
import ru.lukaville.dockerui.presenter.repository.RepositoryListPresenter


val presenterModule = Kodein.Module {
    bind<RegistryListPresenter>() with provider {
        RegistryListPresenter(kodein)
    }

    bind<RepositoryListPresenter>() with provider {
        RepositoryListPresenter(kodein)
    }

    bind<RegistryCreatePresenter>() with provider {
        RegistryCreatePresenter(kodein)
    }
}