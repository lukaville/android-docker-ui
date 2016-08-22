package ru.lukaville.dockerui.repository

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.factory
import com.github.salomonbrys.kodein.provider
import com.github.salomonbrys.kodein.threadSingleton
import ru.lukaville.dockerui.api.registry.service.DockerRegistryService
import ru.lukaville.dockerui.entities.Registry
import ru.lukaville.dockerui.repository.image.ImageRepository
import ru.lukaville.dockerui.repository.image.NetworkImageRepository
import ru.lukaville.dockerui.repository.registry.RealmRegistryRepository
import ru.lukaville.dockerui.repository.registry.RegistryRepository

enum class Storage {
    Database,
    Network
}

val repositoryModule = Kodein.Module {
    bind<RegistryRepository>(Storage.Database) with threadSingleton {
        RealmRegistryRepository(provider())
    }

    bind<ImageRepository>(Storage.Network) with factory {
        registry: Registry ->
        NetworkImageRepository(
                factory<Registry, DockerRegistryService>()(registry)
        )
    }
}