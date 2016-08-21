package ru.lukaville.dockerui.entities

import io.realm.RealmObject

open class Credentials(
        open var user: String = "",
        open var password: String = ""
) : RealmObject()