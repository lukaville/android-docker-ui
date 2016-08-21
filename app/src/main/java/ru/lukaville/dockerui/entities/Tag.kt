package ru.lukaville.dockerui.entities

import io.realm.RealmObject

open class Tag(
        open var name: String = ""
) : RealmObject() {
    override fun toString(): String {
        return name
    }
}