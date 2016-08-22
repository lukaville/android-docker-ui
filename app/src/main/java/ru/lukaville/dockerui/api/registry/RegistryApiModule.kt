package ru.lukaville.dockerui.api.registry

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.factory
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.google.gson.Gson
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.lukaville.dockerui.api.registry.service.DockerRegistryService
import ru.lukaville.dockerui.entities.Registry

enum class Tag {
    LoggingInterceptor,
    AuthInterceptor
}

val registryApiModule = Kodein.Module {
    bind<Gson>() with singleton { Gson() }

    bind<OkHttpClient>() with factory {
        registry: Registry ->
        OkHttpClient.Builder()
                .addNetworkInterceptor(StethoInterceptor())
                .addInterceptor(StethoInterceptor())
                .addInterceptor(instance<Interceptor>(Tag.LoggingInterceptor))
                .addInterceptor(factory<Registry, Interceptor>(Tag.AuthInterceptor)(registry))
                .build()
    }

    bind<Interceptor>(Tag.LoggingInterceptor) with singleton {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        interceptor
    }

    bind<Interceptor>(Tag.AuthInterceptor) with factory {
        registry: Registry ->
        Interceptor {
            chain ->
            val original = chain.request()

            val requestBuilder = original.newBuilder()

            if (registry.credentials.user != "") {
                val basic = Credentials.basic(
                        registry.credentials.user,
                        registry.credentials.password
                )
                requestBuilder.header("Authorization", basic)
            }

            requestBuilder.method(original.method(), original.body())

            val request = requestBuilder.build()
            chain.proceed(request)
        }
    }

    bind<Retrofit>() with factory {
        registry: Registry ->
        Retrofit.Builder()
                .baseUrl(registry.url)
                .client(factory<Registry, OkHttpClient>()(registry))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(instance()))
                .build()
    }

    bind<DockerRegistryService>() with factory {
        registry: Registry ->
        factory<Registry, Retrofit>()(registry)
                .create(DockerRegistryService::class.java)
    }
}