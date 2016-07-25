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
import ru.lukaville.dockerui.ui.view.RegistryListView
import ru.lukaville.dockerui.util.bindView
import java.util.*

class RegistryListFragment : BaseFragment(), RegistryListView {
    val recyclerView: StateRecyclerView by bindView(R.id.recycler_view)

    val emptyView: View by bindView(R.id.empty)
    val errorView: View by bindView(R.id.error)
    val progressView: View by bindView(R.id.progress)

    val addFab: View by bindView(R.id.add)

    lateinit var adapter: RegistryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_registry_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initFab()
    }

    private fun initFab() {
        addFab.setOnClickListener {
            //
        }
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(activity)

        recyclerView.setEmptyView(emptyView)
        recyclerView.setErrorView(errorView)
        recyclerView.setProgressView(progressView)

        adapter = RegistryAdapter()
        adapter.itemClickListener = object : OnItemClickListener {
            override fun onItemClicked(index: Int, view: View) {
                //
            }
        }

        adapter.registries = ArrayList<Registry>()
        for (i in 1..100) {
            adapter.registries.add(Registry("http://abc.xyz", "Test " + i))
        }

        recyclerView.adapter = adapter
    }
}