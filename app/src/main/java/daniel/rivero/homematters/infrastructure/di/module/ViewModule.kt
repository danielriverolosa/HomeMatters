package daniel.rivero.homematters.infrastructure.di.module

import android.app.Activity
import daniel.rivero.homematters.infrastructure.di.scope.ViewScope
import dagger.Module
import dagger.Provides
import daniel.rivero.homematters.presentation.base.BaseActivity
import daniel.rivero.homematters.presentation.base.Navigator


@Module
class ViewModule(private val activity: BaseActivity<*, *>) {

    @Provides
    @ViewScope
    internal fun activity(): Activity {
        return activity
    }

    @Provides
    @ViewScope
    fun providesNavigator(): Navigator = Navigator(activity)

}