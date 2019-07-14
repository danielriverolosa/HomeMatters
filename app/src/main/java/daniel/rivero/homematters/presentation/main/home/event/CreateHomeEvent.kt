package daniel.rivero.homematters.presentation.main.home.event

import daniel.rivero.homematters.presentation.base.Event

sealed class CreateHomeEvent: Event {

    class Continue(val name: String?): CreateHomeEvent()

}