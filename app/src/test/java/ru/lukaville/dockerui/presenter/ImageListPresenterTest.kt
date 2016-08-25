package ru.lukaville.dockerui.presenter

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.factory
import com.github.salomonbrys.kodein.instance
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test
import ru.lukaville.dockerui.entities.Image
import ru.lukaville.dockerui.entities.Registry
import ru.lukaville.dockerui.mock.MockImageListView
import ru.lukaville.dockerui.presenter.image.ImageListPresenter
import ru.lukaville.dockerui.repository.Storage
import ru.lukaville.dockerui.repository.image.ImageRepository
import ru.lukaville.dockerui.repository.registry.RegistryRepository
import ru.lukaville.dockerui.testImageList
import ru.lukaville.dockerui.testRegistry
import ru.lukaville.dockerui.ui.DataState
import ru.lukaville.dockerui.ui.Router
import rx.lang.kotlin.PublishSubject
import rx.subjects.PublishSubject

class ImageListPresenterTest {
    lateinit var presenter: ImageListPresenter
    lateinit var view: MockImageListView

    lateinit var routerMock: Router

    lateinit var registryRepositoryMock: RegistryRepository
    lateinit var registryRepositoryObservable: PublishSubject<Registry>

    lateinit var imageRepositoryMock: ImageRepository
    lateinit var imageRepositoryObservable: PublishSubject<MutableList<Image>>

    @Before
    fun before() {
        routerMock = mock<Router>()

        registryRepositoryObservable = PublishSubject()
        registryRepositoryMock = mock<RegistryRepository> {
            on { getRegistry("http://test") } doReturn registryRepositoryObservable
        }

        imageRepositoryObservable = PublishSubject()
        imageRepositoryMock = mock<ImageRepository> {
            on { getImages() } doReturn imageRepositoryObservable
        }

        val kodein = Kodein {
            bind<Router>() with instance(routerMock)
            bind<RegistryRepository>(Storage.Database) with instance(registryRepositoryMock)
            bind<ImageRepository>(Storage.Network) with factory {
                registry: Registry ->
                imageRepositoryMock
            }
        }

        view = MockImageListView()

        presenter = ImageListPresenter(kodein, "http://test")
        presenter.view = view

        presenter.onCreate()
    }

    @Test
    fun testProgress() {
        presenter.onResume()
        view.getLastEvent() shouldEqual DataState.Progress<MutableList<Image>>()
    }

    @Test
    fun testLoadImages() {
        presenter.onResume()
        view.getLastEvent()

        registryRepositoryObservable.onNext(testRegistry)
        imageRepositoryObservable.onNext(testImageList)
        view.getLastEvent() shouldEqual DataState.Content(testImageList)
    }

    @Test
    fun testImagesEmpty() {
        presenter.onResume()
        view.getLastEvent()

        registryRepositoryObservable.onNext(testRegistry)
        imageRepositoryObservable.onNext(mutableListOf())
        view.getLastEvent() shouldEqual DataState.Empty<MutableList<Image>>()
    }

    @Test
    fun testRegistriesLoadError() {
        presenter.onResume()
        view.getLastEvent()

        val error = RuntimeException()
        registryRepositoryObservable.onError(error)
        view.getLastEvent() shouldEqual DataState.Error<MutableList<Image>>(error)
    }
}