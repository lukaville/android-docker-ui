package ru.lukaville.dockerui.api.registry.service

import retrofit2.http.GET
import rx.Observable

interface DockerRegistryService {
    @GET("v2/")
    fun checkVersion(): Observable<Unit>
}