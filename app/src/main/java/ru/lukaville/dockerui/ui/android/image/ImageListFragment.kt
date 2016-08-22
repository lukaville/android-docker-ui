package ru.lukaville.dockerui.ui.android.image

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.lukaville.dockerui.R
import ru.lukaville.dockerui.entities.Image
import ru.lukaville.dockerui.ui.DataState
import ru.lukaville.dockerui.ui.android.BaseFragment
import ru.lukaville.dockerui.ui.android.core.OnItemClickListener
import ru.lukaville.dockerui.ui.android.core.widget.StateRecyclerView
import ru.lukaville.dockerui.util.bindView
import rx.Observable
import rx.Subscription
import rx.lang.kotlin.PublishSubject

class ImageListFragment : BaseFragment() {
    companion object {
        fun newInstance(): ImageListFragment {
            val arguments = Bundle()

            val fragment = ImageListFragment()
            fragment.arguments = arguments

            return fragment
        }
    }

    val recyclerView: StateRecyclerView by bindView(R.id.recycler_view)

    val emptyView: View by bindView(R.id.empty)
    val errorView: View by bindView(R.id.error)
    val progressView: View by bindView(R.id.progress)

    lateinit var mAdapter: ImageAdapter

    val repositoryClicks = PublishSubject<Image>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_image_list, container, false)
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

        mAdapter = ImageAdapter()
        mAdapter.itemClickListener = object : OnItemClickListener {
            override fun onItemClicked(index: Int, view: View) {
                val repository = mAdapter.images[index]
                repositoryClicks.onNext(repository)
            }
        }

        recyclerView.adapter = mAdapter
    }

    fun subscribeImageList(data: Observable<DataState<MutableList<Image>>>): Subscription {
        return data.subscribe {
            recyclerView.setState(it)

            if (it is DataState.Content) {
                mAdapter.images = it.data
                mAdapter.notifyDataSetChanged()
            }
        }
    }
}