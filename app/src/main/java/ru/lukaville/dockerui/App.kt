package ru.lukaville.dockerui

import android.app.Application
import android.os.StrictMode
import com.facebook.stetho.Stetho
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.lazy
import com.squareup.leakcanary.LeakCanary
import ru.lukaville.dockerui.api.apiModule
import ru.lukaville.dockerui.repository.realmModule
import ru.lukaville.dockerui.repository.repositoryModule

class App : Application(), KodeinAware {
    override val kodein by Kodein.lazy {
        import(apiModule)
        import(repositoryModule)
        import(realmModule)
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            LeakCanary.install(this)
            Stetho.initializeWithDefaults(this)
            StrictMode.enableDefaults()
        }
    }
}