package ru.lukaville.dockerui.ui.android.core.router

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.android.autoActivitySingleton
import ru.lukaville.dockerui.ui.Router


val routerModule = Kodein.Module {
    bind<Router>() with autoActivitySingleton {
        AndroidRouter(it)
    }
}