package ru.lukaville.dockerui.ui.view

import ru.lukaville.dockerui.entities.Image
import ru.lukaville.dockerui.ui.DataState
import rx.Observable
import rx.Subscription

interface ImageListView : BaseView {
    fun subscribeImageList(images: Observable<DataState<MutableList<Image>>>): Subscription
}