package ru.lukaville.dockerui.presenter

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.instance
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import ru.lukaville.dockerui.mock.MockRegistryCreateView
import ru.lukaville.dockerui.presenter.registry.RegistryCreatePresenter
import ru.lukaville.dockerui.repository.Storage
import ru.lukaville.dockerui.repository.registry.RegistryRepository
import ru.lukaville.dockerui.testRegistry

class CreateRegistryPresenterTest {
    lateinit var presenter: RegistryCreatePresenter
    lateinit var view: MockRegistryCreateView

    lateinit var registryRepositoryMock: RegistryRepository

    @Before
    fun before() {
        registryRepositoryMock = mock<RegistryRepository>()

        val kodein = Kodein {
            bind<RegistryRepository>(Storage.Database) with instance(registryRepositoryMock)
        }

        view = MockRegistryCreateView()

        presenter = RegistryCreatePresenter(kodein)
        presenter.view = view

        presenter.onCreate()
    }

    @Test
    fun testCreateRegistry() {
        presenter.onResume()

        view.createRegistryClick(testRegistry)
        verify(registryRepositoryMock).addRegistry(testRegistry)
    }
}