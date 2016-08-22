package ru.lukaville.dockerui.entities

import io.realm.RealmList
import io.realm.RealmObject

open class Image(
        open var name: String = "",
        open var tags: RealmList<Tag> = RealmList()
) : RealmObject()