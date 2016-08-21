package ru.lukaville.dockerui.ui.android.repository

import android.os.Bundle
import com.github.salomonbrys.kodein.instance
import ru.lukaville.dockerui.R
import ru.lukaville.dockerui.presenter.repository.RepositoryListPresenter
import ru.lukaville.dockerui.ui.android.PresentedActivity
import ru.lukaville.dockerui.ui.view.RepositoryListView

class RepositoryListActivity : PresentedActivity<RepositoryListView>(), RepositoryListView {
    companion object {
        const val REGISTRY_URL_EXTRA = "REGISTRY_URL"
    }

    val presenter: RepositoryListPresenter by injector.instance()

    lateinit var repositoryListFragment: RepositoryListFragment

    override fun getLayout(): Int {
        return R.layout.activity_repository_list
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initFragment(intent.extras)
        initPresenter()
    }

    private fun initFragment(extras: Bundle) {
        val registryUrl = extras.getString(REGISTRY_URL_EXTRA)
        repositoryListFragment = RepositoryListFragment.newInstance(registryUrl)

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.repository_list_fragment, repositoryListFragment)
                .commitAllowingStateLoss()
    }

    private fun initPresenter() {
        presenter.view = this
        registerPresenter(presenter)
    }

    override fun getHomeAsUpIndicator(): Int {
        return BACK_ARROW_INDICATOR
    }
}