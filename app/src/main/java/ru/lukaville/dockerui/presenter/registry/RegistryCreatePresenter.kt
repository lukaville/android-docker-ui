package ru.lukaville.dockerui.presenter.registry

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.instance
import ru.lukaville.dockerui.presenter.BasePresenter
import ru.lukaville.dockerui.repository.Storage
import ru.lukaville.dockerui.repository.registry.RegistryRepository
import ru.lukaville.dockerui.ui.view.RegistryCreateView

class RegistryCreatePresenter(override val kodein: Kodein) : BasePresenter<RegistryCreateView>() {
    val registryRepository = kodein.instance<RegistryRepository>(Storage.Database)

    override fun onCreate() {
        view.createRegistry().subscribe {
            registryRepository.addRegistry(it)
        }
    }
}