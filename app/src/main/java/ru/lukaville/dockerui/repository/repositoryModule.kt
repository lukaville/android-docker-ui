package ru.lukaville.dockerui.repository

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.provider
import com.github.salomonbrys.kodein.singleton
import ru.lukaville.dockerui.repository.registry.RealmRegistryRepository
import ru.lukaville.dockerui.repository.registry.RegistryRepository

enum class Storage {
    Database,
    Network
}

val repositoryModule = Kodein.Module {
    bind<RegistryRepository>(Storage.Database) with singleton {
        RealmRegistryRepository(provider())
    }
}