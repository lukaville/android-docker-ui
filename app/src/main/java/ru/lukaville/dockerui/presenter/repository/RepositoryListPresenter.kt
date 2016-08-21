package ru.lukaville.dockerui.presenter.repository

import com.github.salomonbrys.kodein.Kodein
import ru.lukaville.dockerui.presenter.BasePresenter
import ru.lukaville.dockerui.ui.view.RepositoryListView

class RepositoryListPresenter(override val kodein: Kodein) : BasePresenter<RepositoryListView>() {
}