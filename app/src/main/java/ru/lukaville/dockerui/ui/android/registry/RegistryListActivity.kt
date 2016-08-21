package ru.lukaville.dockerui.ui.android.registry

import android.content.Intent
import android.os.Bundle
import android.view.View
import ru.lukaville.dockerui.R
import ru.lukaville.dockerui.ui.android.BaseActivity
import ru.lukaville.dockerui.util.bindView

class RegistryListActivity : BaseActivity() {
    val addFab: View by bindView(R.id.add_fab)

    override fun getLayout(): Int {
        return R.layout.activity_registry_list
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFab()
    }

    private fun initFab() {
        addFab.setOnClickListener {
            val intent = Intent(this, RegistryCreateActivity::class.java)
            startActivity(intent)
        }
    }
}