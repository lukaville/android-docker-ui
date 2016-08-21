package ru.lukaville.dockerui.ui.android.repository

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.realm.RealmList
import ru.lukaville.dockerui.R
import ru.lukaville.dockerui.entities.Repository
import ru.lukaville.dockerui.entities.Tag
import ru.lukaville.dockerui.ui.android.BaseFragment
import ru.lukaville.dockerui.ui.android.core.OnItemClickListener
import ru.lukaville.dockerui.ui.android.core.widget.StateRecyclerView
import ru.lukaville.dockerui.util.bindView
import rx.lang.kotlin.PublishSubject
import java.util.*

class RepositoryListFragment : BaseFragment() {
    companion object {
        const val REGISTRY_URL_ARG = "REGISTRY_URL"

        fun newInstance(registryUrl: String): RepositoryListFragment {
            val arguments = Bundle()
            arguments.putString(REGISTRY_URL_ARG, registryUrl)

            val fragment = RepositoryListFragment()
            fragment.arguments = arguments

            return fragment
        }
    }

    val recyclerView: StateRecyclerView by bindView(R.id.recycler_view)

    val emptyView: View by bindView(R.id.empty)
    val errorView: View by bindView(R.id.error)
    val progressView: View by bindView(R.id.progress)

    lateinit var mAdapter: RepositoryAdapter

    val repositoryClicks = PublishSubject<Repository>()

    lateinit var registryUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registryUrl = arguments.getString(REGISTRY_URL_ARG)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_registry_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(activity)

        recyclerView.setEmptyView(emptyView)
        recyclerView.setErrorView(errorView)
        recyclerView.setProgressView(progressView)

        mAdapter = RepositoryAdapter()
        mAdapter.itemClickListener = object : OnItemClickListener {
            override fun onItemClicked(index: Int, view: View) {
                val repository = mAdapter.repositories[index]
                repositoryClicks.onNext(repository)
            }
        }

        mAdapter.repositories = ArrayList<Repository>()
        for (i in 1..10) {
            val tags = RealmList<Tag>(Tag("abc"), Tag(registryUrl))
            mAdapter.repositories.add(Repository("test_sds", tags))
        }

        recyclerView.adapter = mAdapter
    }
}