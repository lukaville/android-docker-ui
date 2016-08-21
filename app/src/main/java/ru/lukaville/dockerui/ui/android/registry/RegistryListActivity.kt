package ru.lukaville.dockerui.ui.android.registry

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.github.salomonbrys.kodein.instance
import com.jakewharton.rxbinding.view.clicks
import ru.lukaville.dockerui.R
import ru.lukaville.dockerui.entities.Registry
import ru.lukaville.dockerui.presenter.registry.RegistryListPresenter
import ru.lukaville.dockerui.ui.android.PresentedActivity
import ru.lukaville.dockerui.ui.view.RegistryListView
import ru.lukaville.dockerui.util.bindView
import rx.Observable
import rx.Subscription

class RegistryListActivity : PresentedActivity<RegistryListView>(), RegistryListView {
    val presenter: RegistryListPresenter by injector.instance()

    val addFab: View by bindView(R.id.add_fab)
    lateinit var registryListFragment: RegistryListFragment

    override fun getLayout(): Int {
        return R.layout.activity_registry_list
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initFragment()
        initFab()
        initPresenter()
    }

    private fun initFragment() {
        registryListFragment = RegistryListFragment.newInstance()

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.registry_list_fragment, registryListFragment)
                .commitAllowingStateLoss()
    }

    private fun initPresenter() {
        presenter.view = this
        registerPresenter(presenter)
    }

    private fun initFab() {
        addFab.setOnClickListener {
            val intent = Intent(this, RegistryCreateActivity::class.java)
            startActivity(intent)
        }
    }

    override fun registryClicks(): Observable<Registry> {
        return registryListFragment.registryClicks
    }

    override fun createRegistry(): Observable<Unit> {
        return addFab.clicks()
    }

    override fun subscribeRegistryList(registries: Observable<MutableList<Registry>>): Subscription {
        return registryListFragment.subscribeRegistryList(registries)
    }
}