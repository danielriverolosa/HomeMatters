package daniel.rivero.homematters.presentation.main.home.event

import daniel.rivero.homematters.domain.Home
import daniel.rivero.homematters.domain.User
import daniel.rivero.homematters.presentation.base.Event

sealed class EditHomeEvent: Event {

    class LoadData(val home: Home, val userList: List<User>): EditHomeEvent()
    class Continue(val homeName: String, val user: User): EditHomeEvent()

}