package ru.lukaville.dockerui.repository

import android.content.Context
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import io.realm.Realm
import io.realm.RealmConfiguration

val realmModule = Kodein.Module {
    bind<Realm>() with singleton {
        Realm.getInstance(instance())
    }

    bind<RealmConfiguration>() with singleton {
        RealmConfiguration
                .Builder(instance<Context>())
                .deleteRealmIfMigrationNeeded()
                .build()
    }
}