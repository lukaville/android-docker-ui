package ru.lukaville.dockerui.repository.image

import ru.lukaville.dockerui.entities.Image
import rx.Observable

interface ImageRepository {
    fun getImages(): Observable<MutableList<Image>>
    fun setImages(images: MutableList<Image>)
}