package daniel.rivero.homematters.infrastructure.di.module

import android.content.Context
import daniel.rivero.homematters.infrastructure.AndroidApplication
import dagger.Module
import dagger.Provides
import daniel.rivero.homematters.data.datasource.api.ApiClientGenerator
import daniel.rivero.homematters.data.datasource.api.RetrofitApiClientGenerator
import daniel.rivero.homematters.data.repository.SecurityDataRepository
import daniel.rivero.homematters.data.repository.utils.ApiResponseHandler
import daniel.rivero.homematters.data.repository.utils.ApiResponseHandlerImpl
import daniel.rivero.homematters.domain.repository.SecurityRepository
import javax.inject.Singleton


@Module
class ApplicationModule(private val application: AndroidApplication) {

    @Provides
    @Singleton
    fun providesApplicationContext(): AndroidApplication {
        return application
    }

    @Provides
    @Singleton
    fun providesApiClientGenerator(apiClientGenerator: RetrofitApiClientGenerator): ApiClientGenerator = apiClientGenerator

    @Provides
    @Singleton
    fun providesApiResponseHandler(apiResponseHandler: ApiResponseHandlerImpl): ApiResponseHandler = apiResponseHandler

    @Provides
    @Singleton
    fun providesSecurityRepository(securityDataRepository: SecurityDataRepository): SecurityRepository = securityDataRepository
}