package ru.lukaville.dockerui.presenter.image

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.factory
import com.github.salomonbrys.kodein.instance
import ru.lukaville.dockerui.entities.Credentials
import ru.lukaville.dockerui.entities.Image
import ru.lukaville.dockerui.entities.Registry
import ru.lukaville.dockerui.presenter.BasePresenter
import ru.lukaville.dockerui.repository.Storage
import ru.lukaville.dockerui.repository.image.ImageRepository
import ru.lukaville.dockerui.repository.registry.RegistryRepository
import ru.lukaville.dockerui.ui.DataState
import ru.lukaville.dockerui.ui.view.ImageListView
import rx.lang.kotlin.PublishSubject
import rx.subscriptions.CompositeSubscription

class ImageListPresenter(override val kodein: Kodein, val apiUrl: String) : BasePresenter<ImageListView>() {
    val subscription: CompositeSubscription = CompositeSubscription()
    val registryRepository: RegistryRepository = kodein.instance(Storage.Database)
    val imageRepositoryFactory: (Registry) -> ImageRepository = kodein.factory<Registry, ImageRepository>(Storage.Network)

    val data = PublishSubject<DataState<MutableList<Image>>>()

    override fun onResume() {
        super.onResume()

        subscription.add(
                view.subscribeImageList(
                        data
                )
        )

        data.onNext(
                DataState.Progress()
        )

        subscription.add(
                registryRepository
                        .getRegistry(apiUrl)
                        .flatMap {
                            val credentials = Credentials(
                                    it.credentials.user,
                                    it.credentials.password
                            )

                            val registry = Registry(
                                    it.url,
                                    it.name,
                                    credentials
                            )

                            imageRepositoryFactory(registry)
                                    .getImages()
                        }
                        .map {
                            if (it.size == 0) {
                                DataState.Empty<MutableList<Image>>()
                            } else {
                                DataState.Content(it)
                            }
                        }
                        .onErrorReturn {
                            DataState.Error(it.cause)
                        }
                        .subscribe(data)
        )
    }

    override fun onPause() {
        super.onPause()
        subscription.unsubscribe()
    }
}