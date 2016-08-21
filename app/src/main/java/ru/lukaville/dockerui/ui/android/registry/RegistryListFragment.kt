package ru.lukaville.dockerui.ui.android.registry

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.lukaville.dockerui.R
import ru.lukaville.dockerui.entities.registry.Registry
import ru.lukaville.dockerui.ui.android.BaseFragment
import ru.lukaville.dockerui.ui.android.core.OnItemClickListener
import ru.lukaville.dockerui.ui.android.core.widget.StateRecyclerView
import ru.lukaville.dockerui.util.bindView
import rx.lang.kotlin.PublishSubject
import java.util.*

class RegistryListFragment : BaseFragment() {
    companion object {
        fun newInstance(): RegistryListFragment {
            return RegistryListFragment()
        }
    }

    val recyclerView: StateRecyclerView by bindView(R.id.recycler_view)

    val emptyView: View by bindView(R.id.empty)
    val errorView: View by bindView(R.id.error)
    val progressView: View by bindView(R.id.progress)

    lateinit var adapter: RegistryAdapter

    val registryClicks = PublishSubject<Registry>()

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

        adapter = RegistryAdapter()
        adapter.itemClickListener = object : OnItemClickListener {
            override fun onItemClicked(index: Int, view: View) {
                val registry = adapter.registries[index]
                registryClicks.onNext(registry)
            }
        }

        adapter.registries = ArrayList<Registry>()
        for (i in 1..100) {
            adapter.registries.add(Registry("http://abc.xyz", "Test " + i))
        }

        recyclerView.adapter = adapter
    }
}