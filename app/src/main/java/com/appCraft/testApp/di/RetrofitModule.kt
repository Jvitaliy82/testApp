@file:Suppress("PackageDirectoryMismatch")

import android.content.Context
import com.appCraft.data.BuildConfig
import com.appCraft.data.network.interceptor.ErrorResponseInterceptor
import com.appCraft.data.network.interceptor.HeaderInterceptor
import com.appCraft.domain.global.SchedulersProvider
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

val retrofitModule = module {
    val responsesPath = "responses"
    val cacheMaxSize = 100 * 1024 * 1024 // 100 MB
    val timeout = 30_000L // 30 sec

    fun getCache(context: Context): Cache? {
        var cache: Cache? = null
        try {
            val httpCacheDirectory = File(context.cacheDir, responsesPath)
            cache = Cache(httpCacheDirectory, cacheMaxSize.toLong())
        } catch (ignored: Exception) {
        }

        return cache
    }

    fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = if (BuildConfig.DEBUG) BODY else NONE
        return httpLoggingInterceptor
    }

    factory(named("AuthClient")) {
        OkHttpClient.Builder()
                //.addInterceptor(ChuckerInterceptor(androidContext()))
                .addInterceptor(HeaderInterceptor(get()))
                .addInterceptor(getHttpLoggingInterceptor())
                .addInterceptor(ErrorResponseInterceptor(get()))
                .cache(getCache(androidContext()))
                .callTimeout(timeout, TimeUnit.MILLISECONDS)
                .connectTimeout(timeout, TimeUnit.MILLISECONDS)
                .readTimeout(timeout, TimeUnit.MILLISECONDS)
                .writeTimeout(timeout, TimeUnit.MILLISECONDS)
                .build()
    }

    factory(named("CommonClient")) {
        OkHttpClient.Builder()
                //.addInterceptor(ChuckerInterceptor(androidContext()))
                .addInterceptor(HeaderInterceptor(get()))
                .addInterceptor(getHttpLoggingInterceptor())
                .addInterceptor(ErrorResponseInterceptor(get()))
                .cache(getCache(androidContext()))
                .callTimeout(timeout, TimeUnit.MILLISECONDS)
                .connectTimeout(timeout, TimeUnit.MILLISECONDS)
                .readTimeout(timeout, TimeUnit.MILLISECONDS)
                .writeTimeout(timeout, TimeUnit.MILLISECONDS)
                .build()
    }

    fun getBuilder(
        provider: SchedulersProvider,
        okHttpClient: OkHttpClient
    ): Retrofit.Builder {
        val gson = GsonBuilder().serializeNulls().create()
        return Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(provider.io()))
                .addConverterFactory(GsonConverterFactory.create(gson))
    }

    factory(named("CommonBuilder")) {
        getBuilder(provider = get(), okHttpClient = get(named("CommonClient")))
    }

    factory(named("AuthBuilder")) {
        getBuilder(provider = get(), okHttpClient = get(named("AuthClient")))
    }

    single(named("AuthRetrofit")) {
        get<Retrofit.Builder>(named("AuthBuilder"))
                .baseUrl(BuildConfig.API_ENDPOINT).build()
    }

    single(named("CommonRetrofit")) {
        get<Retrofit.Builder>(named("CommonBuilder"))
                .baseUrl(BuildConfig.API_ENDPOINT).build()
    }
}
