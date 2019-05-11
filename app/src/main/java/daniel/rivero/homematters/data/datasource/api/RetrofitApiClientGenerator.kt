package daniel.rivero.homematters.data.datasource.api

import com.google.gson.GsonBuilder
import daniel.rivero.homematters.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.reflect.KClass

class RetrofitApiClientGenerator @Inject constructor() : ApiClientGenerator {

    private val retrofit: Retrofit

    init {
        val builder = Retrofit.Builder()
            .baseUrl(BuildConfig.base_url)
            .addConverterFactory(jsonConverterFactory())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(buildHttpClient())

        retrofit = builder.build()
    }

    private fun jsonConverterFactory(): GsonConverterFactory {
        val gson = GsonBuilder()
            .enableComplexMapKeySerialization()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create()

        return GsonConverterFactory.create(gson)
    }

    private fun buildHttpClient(): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor())
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)

        return httpClientBuilder.build()
    }

    private fun httpLoggingInterceptor(): Interceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return loggingInterceptor
    }

    override fun <T: Any> generateApi(service: KClass<T>): T {
        return retrofit.create(service.java)
    }
}