package ru.lukaville.dockerui.entities.registry

import io.realm.RealmObject

open class Credentials(
        open var user: String = "",
        open var password: String = ""
) : RealmObject()