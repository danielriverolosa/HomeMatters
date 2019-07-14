package daniel.rivero.homematters.presentation.main.user.event

import daniel.rivero.homematters.domain.User
import daniel.rivero.homematters.presentation.base.Event

sealed class EditUserEvent: Event {

    class Initialize(val user: User): EditUserEvent()
    class EffortChange(val value: Int): EditUserEvent()
    class Continue(val name: String, val value: Int): EditUserEvent()
}