package ru.lukaville.dockerui.ui

import ru.lukaville.dockerui.entities.Registry

interface Router {
    fun detailRegistry(registry: Registry)
    fun createRegistry()
}