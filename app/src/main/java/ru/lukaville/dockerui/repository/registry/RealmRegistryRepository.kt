package ru.lukaville.dockerui.repository.registry

import io.realm.Realm
import ru.lukaville.dockerui.entities.Registry
import rx.Observable

class RealmRegistryRepository(val realm: () -> Realm) : RegistryRepository {
    override fun getRegistries(): Observable<MutableList<Registry>> {
        return realm()
                .where(Registry::class.java)
                .findAll()
                .asObservable()
                .map { realm().copyFromRealm(it) }
    }

    override fun getRegistry(url: String): Observable<Registry> {
        return realm()
                .where(Registry::class.java)
                .equalTo("url", url)
                .findFirst()
                .asObservable()
    }

    override fun addRegistry(registry: Registry) {
        realm().executeTransaction {
            it.copyToRealm(registry)
        }
    }

    override fun removeRegistry(registry: Registry) {
        realm().executeTransaction {
            registry.deleteFromRealm()
        }
    }

    override fun clearRegistries() {
        realm().executeTransaction {
            it.delete(Registry::class.java)
        }
    }
}