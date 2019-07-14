package daniel.rivero.homematters.infrastructure.di.component

import daniel.rivero.homematters.infrastructure.di.module.ViewModule
import daniel.rivero.homematters.infrastructure.di.scope.ViewScope
import dagger.Subcomponent
import daniel.rivero.homematters.presentation.login.AuthActivity


@ViewScope
@Subcomponent(
        modules = [
            ViewModule::class
        ]
)
interface ViewComponent {

    fun inject(activity: AuthActivity)
}