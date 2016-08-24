package ru.lukaville.dockerui.ui.android.core.router

import android.app.Activity
import android.content.Intent
import ru.lukaville.dockerui.entities.Registry
import ru.lukaville.dockerui.ui.Router
import ru.lukaville.dockerui.ui.android.image.ImageListActivity
import ru.lukaville.dockerui.ui.android.registry.RegistryCreateActivity

class AndroidRouter(val activity: Activity): Router {
    override fun detailRegistry(registry: Registry) {
        val intent = Intent(activity, ImageListActivity::class.java)
        intent.putExtra(ImageListActivity.REGISTRY_URL_EXTRA, registry.url)
        activity.startActivity(intent)
    }

    override fun createRegistry() {
        val intent = Intent(activity, RegistryCreateActivity::class.java)
        activity.startActivity(intent)
    }
}