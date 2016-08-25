package ru.lukaville.dockerui

import io.realm.RealmList
import ru.lukaville.dockerui.entities.Credentials
import ru.lukaville.dockerui.entities.Image
import ru.lukaville.dockerui.entities.Registry
import ru.lukaville.dockerui.entities.Tag

val testImageList = mutableListOf(
        Image("test1", RealmList(Tag("abc"), Tag("def"))),
        Image("test2", RealmList(Tag("qwe"), Tag("rty")))
)

val testOtherImageList = mutableListOf(
        Image("test1", RealmList(Tag("abc"), Tag("def"))),
        Image("test2", RealmList(Tag("qwe"), Tag("rty"))),
        Image("test3", RealmList(Tag("asd"), Tag("fgh")))
)

val testRegistry = Registry("http://test", "name", Credentials("admin", "pass"))

val testRegistryList = mutableListOf(
        Registry("http://test1", "name1", Credentials("admin", "pass")),
        Registry("http://test2", "name2", Credentials("admin", "pass"))
)

val testOtherRegistryList = mutableListOf(
        Registry("http://test1", "name1", Credentials("admin", "pass")),
        Registry("http://test2", "name2", Credentials("admin", "pass")),
        Registry("http://test3", "name3", Credentials("admin", "pass"))
)