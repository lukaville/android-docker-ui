package ru.lukaville.dockerui.repository.image

import io.realm.Realm
import ru.lukaville.dockerui.entities.Image
import rx.Observable

class RealmImageRepository(val realm: () -> Realm) : ImageRepository {
    override fun getImages(): Observable<MutableList<Image>> {
        return getImages()
//        return realm()
//                .where(Image::class.java)
//                .findAll()
//                .asObservable()
//                .map { realm().copyFromRealm(it) }
    }

    override fun setImages(images: MutableList<Image>) {
    }
}