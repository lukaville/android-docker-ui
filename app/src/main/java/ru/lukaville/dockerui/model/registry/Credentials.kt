package ru.lukaville.dockerui.model.registry

import io.realm.RealmObject

open class Credentials(
        open var user: String = "",
        open var password: String = ""
) : RealmObject()