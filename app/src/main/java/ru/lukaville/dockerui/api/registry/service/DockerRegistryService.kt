package ru.lukaville.dockerui.api.registry.service

import retrofit2.http.GET
import retrofit2.http.Path
import ru.lukaville.dockerui.api.registry.response.CatalogResponse
import ru.lukaville.dockerui.api.registry.response.TagsResponse
import rx.Observable

interface DockerRegistryService {
    @GET("v2/")
    fun checkVersion(): Observable<Unit>

    @GET("v2/_catalog")
    fun getRepositories(): Observable<CatalogResponse>

    @GET("v2/{repository}/tags/list")
    fun getTags(@Path("repository") repository: String): Observable<TagsResponse>
}