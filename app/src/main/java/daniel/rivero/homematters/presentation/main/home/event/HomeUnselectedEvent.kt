package daniel.rivero.homematters.presentation.main.home.event

import daniel.rivero.homematters.presentation.base.Event

sealed class HomeUnselectedEvent: Event {
    object NewHome: HomeUnselectedEvent()
}