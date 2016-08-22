package ru.lukaville.dockerui.ui.android.image

import android.os.Bundle
import com.github.salomonbrys.kodein.factory
import ru.lukaville.dockerui.R
import ru.lukaville.dockerui.entities.Image
import ru.lukaville.dockerui.presenter.image.ImageListPresenter
import ru.lukaville.dockerui.ui.DataState
import ru.lukaville.dockerui.ui.android.PresentedActivity
import ru.lukaville.dockerui.ui.view.ImageListView
import rx.Observable
import rx.Subscription

class ImageListActivity : PresentedActivity<ImageListView>(), ImageListView {
    companion object {
        const val REGISTRY_URL_EXTRA = "REGISTRY_URL"
    }

    val mPresenterFactory: (String) -> ImageListPresenter by factory<String, ImageListPresenter>()
    lateinit var mPresenter: ImageListPresenter

    lateinit var mImageListFragment: ImageListFragment

    override fun getLayout(): Int {
        return R.layout.activity_image_list
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initFragment()
        initPresenter(intent.extras)
    }

    private fun initFragment() {
        mImageListFragment = ImageListFragment.newInstance()

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.image_list_fragment, mImageListFragment)
                .commitAllowingStateLoss()
    }

    private fun initPresenter(extras: Bundle) {
        mPresenter = mPresenterFactory(extras.getString(REGISTRY_URL_EXTRA))
        mPresenter.view = this
        registerPresenter(mPresenter)
    }

    override fun getHomeAsUpIndicator(): Int {
        return BACK_ARROW_INDICATOR
    }

    override fun subscribeImageList(images: Observable<DataState<MutableList<Image>>>): Subscription {
        return mImageListFragment.subscribeImageList(images)
    }
}