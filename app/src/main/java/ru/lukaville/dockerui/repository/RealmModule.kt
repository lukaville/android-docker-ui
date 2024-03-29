package ru.lukaville.dockerui.repository

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.android.autoActivitySingleton
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.threadSingleton
import io.realm.Realm
import io.realm.RealmConfiguration

val realmModule = Kodein.Module {
    bind<Realm>() with threadSingleton {
        Realm.getInstance(instance())
    }

    bind<RealmConfiguration>() with autoActivitySingleton {
        RealmConfiguration
                .Builder(it)
                .deleteRealmIfMigrationNeeded()
                .build()
    }
}