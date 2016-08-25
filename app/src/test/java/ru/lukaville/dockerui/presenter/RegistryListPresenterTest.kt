package ru.lukaville.dockerui.presenter

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.instance
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test
import ru.lukaville.dockerui.entities.Registry
import ru.lukaville.dockerui.mock.MockRegistryListView
import ru.lukaville.dockerui.presenter.registry.RegistryListPresenter
import ru.lukaville.dockerui.repository.Storage
import ru.lukaville.dockerui.repository.registry.RegistryRepository
import ru.lukaville.dockerui.testOtherRegistryList
import ru.lukaville.dockerui.testRegistry
import ru.lukaville.dockerui.testRegistryList
import ru.lukaville.dockerui.ui.DataState
import ru.lukaville.dockerui.ui.Router
import rx.lang.kotlin.PublishSubject
import rx.subjects.PublishSubject

class RegistryListPresenterTest {
    lateinit var presenter: RegistryListPresenter
    lateinit var view: MockRegistryListView

    lateinit var routerMock: Router
    lateinit var registryRepositoryMock: RegistryRepository

    lateinit var registryRepositoryObservable: PublishSubject<MutableList<Registry>>

    @Before
    fun before() {
        routerMock = mock<Router>()

        registryRepositoryObservable = PublishSubject()
        registryRepositoryMock = mock<RegistryRepository> {
            on { getRegistries() } doReturn registryRepositoryObservable
        }

        val kodein = Kodein {
            bind<Router>() with instance(routerMock)
            bind<RegistryRepository>(Storage.Database) with instance(registryRepositoryMock)
        }

        view = MockRegistryListView()

        presenter = RegistryListPresenter(kodein)
        presenter.view = view

        presenter.onCreate()
    }

    @Test
    fun testClearRegistries() {
        view.clearRegistriesClick()
        verify(presenter.registryRepository).clearRegistries()
    }

    @Test
    fun testDetailRegistry() {
        view.registryClick(testRegistry)
        verify(routerMock).detailRegistry(testRegistry)
    }

    @Test
    fun testCreateRegistry() {
        view.createRegistryClick()
        verify(routerMock).createRegistry()
    }

    @Test
    fun testProgress() {
        presenter.onResume()
        view.getLastEvent() shouldEqual DataState.Progress<MutableList<Registry>>()
    }

    @Test
    fun testLoadRegistries() {
        presenter.onResume()
        view.getLastEvent()

        registryRepositoryObservable.onNext(testRegistryList)
        view.getLastEvent() shouldEqual DataState.Content(testRegistryList)
    }

    @Test
    fun testRegistriesEmpty() {
        presenter.onResume()
        view.getLastEvent()

        registryRepositoryObservable.onNext(mutableListOf())
        view.getLastEvent() shouldEqual DataState.Empty<MutableList<Registry>>()
    }


    @Test
    fun testRegistriesLoadError() {
        presenter.onResume()
        view.getLastEvent()

        val error = RuntimeException()
        registryRepositoryObservable.onError(error)
        view.getLastEvent() shouldEqual DataState.Error<MutableList<Registry>>(error)
    }

    @Test
    fun testPauseResume() {
        presenter.onResume()
        view.getLastEvent()

        registryRepositoryObservable.onNext(testRegistryList)
        view.getLastEvent() shouldEqual DataState.Content(testRegistryList)

        presenter.onPause()

        // Return to previous screen
        presenter.onResume()
        view.getLastEvent()

        registryRepositoryObservable.onNext(testOtherRegistryList)
        view.getLastEvent() shouldEqual DataState.Content(testOtherRegistryList)

        presenter.onPause()
    }
}