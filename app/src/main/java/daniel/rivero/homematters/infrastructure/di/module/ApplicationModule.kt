package daniel.rivero.homematters.infrastructure.di.module

import android.content.Context
import android.content.SharedPreferences
import daniel.rivero.homematters.infrastructure.AndroidApplication
import dagger.Module
import dagger.Provides
import daniel.rivero.homematters.data.datasource.api.ApiClientGenerator
import daniel.rivero.homematters.data.datasource.api.RetrofitApiClientGenerator
import daniel.rivero.homematters.data.repository.*
import daniel.rivero.homematters.data.repository.utils.ApiResponseHandler
import daniel.rivero.homematters.data.repository.utils.ApiResponseHandlerImpl
import daniel.rivero.homematters.data.service.PreferenceServiceImpl
import daniel.rivero.homematters.domain.repository.*
import daniel.rivero.homematters.domain.service.PreferenceService
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
    fun providePreferenceService(): PreferenceService {
        return PreferenceServiceImpl(application.getSharedPreferences("settings", Context.MODE_PRIVATE))
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

    @Provides
    @Singleton
    fun providesTaskRepository(taskDataRepository: TaskDataRepository): TaskRepository = taskDataRepository

    @Provides
    @Singleton
    fun providesUserRepository(userDataRepository: UserDataRepository): UserRepository = userDataRepository

    @Provides
    @Singleton
    fun providesHomeRepository(homeDataRepository: HomeDataRepository): HomeRepository = homeDataRepository

    @Provides
    @Singleton
    fun providesStatsRepository(statsDataRepository: StatsDataRepository): StatsRepository = statsDataRepository

}