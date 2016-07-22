package ru.lukaville.dockerui.repository.registry

import ru.lukaville.dockerui.model.registry.Registry
import rx.Observable

interface RegistryRepository {
    fun getRegistries(): Observable<List<Registry>>
    fun getRegistry(url: String): Observable<Registry>
    fun addRegistry(registry: Registry)
    fun removeRegistry(registry: Registry)
}