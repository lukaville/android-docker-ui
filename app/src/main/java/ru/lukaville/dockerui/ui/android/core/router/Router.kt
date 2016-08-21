package ru.lukaville.dockerui.ui.android.core.router

import android.app.Activity
import android.content.Intent
import ru.lukaville.dockerui.entities.Registry
import ru.lukaville.dockerui.ui.android.registry.RegistryCreateActivity
import ru.lukaville.dockerui.ui.android.repository.RepositoryListActivity

class Router(val activity: Activity) {
    fun detailRegistry(registry: Registry) {
        val intent = Intent(activity, RepositoryListActivity::class.java)
        intent.putExtra(RepositoryListActivity.REGISTRY_URL_EXTRA, registry.url)
        activity.startActivity(intent)
    }

    fun createRegistry() {
        val intent = Intent(activity, RegistryCreateActivity::class.java)
        activity.startActivity(intent)
    }
}