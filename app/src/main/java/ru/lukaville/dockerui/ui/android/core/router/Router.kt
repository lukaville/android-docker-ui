package ru.lukaville.dockerui.ui.android.core.router

import android.app.Activity
import android.content.Intent
import ru.lukaville.dockerui.entities.Registry
import ru.lukaville.dockerui.ui.android.registry.RegistryCreateActivity
import ru.lukaville.dockerui.ui.android.image.ImageListActivity

class Router(val activity: Activity) {
    fun detailRegistry(registry: Registry) {
        val intent = Intent(activity, ImageListActivity::class.java)
        intent.putExtra(ImageListActivity.REGISTRY_URL_EXTRA, registry.url)
        activity.startActivity(intent)
    }

    fun createRegistry() {
        val intent = Intent(activity, RegistryCreateActivity::class.java)
        activity.startActivity(intent)
    }
}