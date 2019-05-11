package daniel.rivero.homematters.infrastructure.di.component

import daniel.rivero.homematters.infrastructure.AndroidApplication
import daniel.rivero.homematters.infrastructure.di.module.ApplicationModule
import daniel.rivero.homematters.infrastructure.di.module.ViewModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
        modules = [ApplicationModule::class]
)
interface ApplicationComponent {

    fun inject(application: AndroidApplication)

    fun viewComponent(viewModule: ViewModule): ViewComponent
}