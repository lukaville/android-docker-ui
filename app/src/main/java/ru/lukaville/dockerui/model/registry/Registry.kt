package ru.lukaville.dockerui.model.registry

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Registry(
        @PrimaryKey
        open var url: String = "",
        open var name: String = "",
        open var credentials: Credentials = Credentials()
) : RealmObject()