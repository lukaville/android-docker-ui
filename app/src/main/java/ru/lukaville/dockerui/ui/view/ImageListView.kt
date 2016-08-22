package ru.lukaville.dockerui.ui.view

import ru.lukaville.dockerui.entities.Image
import rx.Observable
import rx.Subscription

interface ImageListView : BaseView {
    fun subscribeImageList(images: Observable<MutableList<Image>>): Subscription
}