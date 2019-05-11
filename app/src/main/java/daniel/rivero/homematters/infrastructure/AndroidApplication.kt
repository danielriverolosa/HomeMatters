package daniel.rivero.homematters.infrastructure

import android.app.Application
import daniel.rivero.homematters.BuildConfig
import daniel.rivero.homematters.infrastructure.di.component.ApplicationComponent
import daniel.rivero.homematters.infrastructure.di.component.DaggerApplicationComponent
import daniel.rivero.homematters.infrastructure.di.module.ApplicationModule
import timber.log.Timber

class AndroidApplication: Application() {

    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    override fun onCreate() {
        applicationComponent.inject(this)
        super.onCreate()
        initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}