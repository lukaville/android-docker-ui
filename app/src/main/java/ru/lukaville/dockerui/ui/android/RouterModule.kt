package ru.lukaville.dockerui.ui.android

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.android.autoActivitySingleton


val routerModule = Kodein.Module {
    bind<Router>() with autoActivitySingleton {
        Router(it)
    }
}