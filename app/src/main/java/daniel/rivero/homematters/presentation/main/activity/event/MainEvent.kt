package daniel.rivero.homematters.presentation.main.activity.event

import androidx.annotation.IdRes
import daniel.rivero.homematters.presentation.base.Event

sealed class MainEvent: Event {

    object Initialize: MainEvent()
    class Navigation(@IdRes val menuAction: Int): MainEvent()

}