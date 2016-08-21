package ru.lukaville.dockerui.ui.android

import android.app.Activity
import android.content.Intent
import android.util.Log
import ru.lukaville.dockerui.entities.registry.Registry
import ru.lukaville.dockerui.ui.android.registry.RegistryCreateActivity

class Router(val activity: Activity) {
    fun detailRegistry(registry: Registry) {
        Log.d("Router", "Open registry: " + registry.toString())
    }

    fun createRegistry() {
        val intent = Intent(activity, RegistryCreateActivity::class.java)
        activity.startActivity(intent)
    }
}