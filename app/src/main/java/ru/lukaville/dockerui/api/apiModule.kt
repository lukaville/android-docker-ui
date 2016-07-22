package ru.lukaville.dockerui.api

import com.github.salomonbrys.kodein.Kodein
import ru.lukaville.dockerui.api.registry.registryApiModule


val apiModule = Kodein.Module {
    import(registryApiModule)
}