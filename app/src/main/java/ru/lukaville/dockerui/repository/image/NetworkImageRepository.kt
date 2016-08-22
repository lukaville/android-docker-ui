package ru.lukaville.dockerui.repository.image

import io.realm.RealmList
import ru.lukaville.dockerui.api.registry.response.TagsResponse
import ru.lukaville.dockerui.api.registry.service.DockerRegistryService
import ru.lukaville.dockerui.entities.Image
import ru.lukaville.dockerui.entities.Tag
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class NetworkImageRepository(val api: DockerRegistryService) : ImageRepository {
    override fun getImages(): Observable<MutableList<Image>> {
        return api
                .getImages()
                .map {
                    it.repositories
                }
                .concatMap {
                    Observable.from(it)
                }
                .concatMap {
                    api.getTags(it)
                }
                .map {
                    respToImage(it)
                }
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }

    fun respToImage(resp: TagsResponse): Image {
        val tags = RealmList<Tag>()
        resp.tags?.forEach {
            tags.add(Tag(it))
        }
        return Image(resp.name, tags)
    }

    override fun setImages(images: MutableList<Image>) {
        throw RuntimeException("Not implemented")
    }
}