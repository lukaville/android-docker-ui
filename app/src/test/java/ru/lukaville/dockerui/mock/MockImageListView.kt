package ru.lukaville.dockerui.mock

import ru.lukaville.dockerui.entities.Image
import ru.lukaville.dockerui.ui.DataState
import ru.lukaville.dockerui.ui.view.ImageListView
import rx.Observable
import rx.Subscription

class MockImageListView : ImageListView {
    val dataEvents = mutableListOf<DataState<MutableList<Image>>>()

    override fun subscribeImageList(images: Observable<DataState<MutableList<Image>>>): Subscription {
        return images
                .subscribe {
                    dataEvents.add(it)
                }
    }

    fun getLastEvent(): DataState<MutableList<Image>> {
        val event = dataEvents.last()
        dataEvents.removeAt(dataEvents.size - 1)
        return event
    }
}
