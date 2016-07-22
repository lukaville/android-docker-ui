package ru.lukaville.dockerui.api.registry

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.lukaville.dockerui.api.registry.service.DockerRegistryService

enum class Tag {
    RegistryBaseUrl,
    LoggingInterceptor
}

val registryApiModule = Kodein.Module {
    constant(Tag.RegistryBaseUrl) with "https://registry.pushka.xyz:5000/"

    bind<Gson>() with singleton { Gson() }

    bind<OkHttpClient>() with singleton {
        OkHttpClient.Builder()
                .addNetworkInterceptor(StethoInterceptor())
                .addInterceptor(StethoInterceptor())
                .addInterceptor(instance<HttpLoggingInterceptor>(Tag.LoggingInterceptor))
                .build()
    }

    bind<HttpLoggingInterceptor>(Tag.LoggingInterceptor) with singleton {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        interceptor
    }

    bind<Retrofit>() with singleton {
        Retrofit.Builder()
                .baseUrl(instance<String>(Tag.RegistryBaseUrl))
                .client(instance())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(instance()))
                .build()
    }

    bind<DockerRegistryService>() with singleton {
        instance<Retrofit>().create(DockerRegistryService::class.java)
    }
}