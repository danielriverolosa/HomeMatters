package daniel.rivero.homematters.presentation.main.home.event

import daniel.rivero.homematters.domain.Home
import daniel.rivero.homematters.presentation.base.Event

sealed class AddUserEvent: Event {

    class FindUser(val email: String): AddUserEvent()
    class AddUser(val email: String, val home: Home): AddUserEvent()

}