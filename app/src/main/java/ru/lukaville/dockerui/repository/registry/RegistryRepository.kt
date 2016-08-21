package ru.lukaville.dockerui.repository.registry

import ru.lukaville.dockerui.entities.Registry
import rx.Observable

interface RegistryRepository {
    fun getRegistries(): Observable<MutableList<Registry>>
    fun getRegistry(url: String): Observable<Registry>
    fun addRegistry(registry: Registry)
    fun removeRegistry(registry: Registry)
}