package daniel.rivero.homematters.presentation.main.home.event

import daniel.rivero.homematters.domain.Home
import daniel.rivero.homematters.domain.User
import daniel.rivero.homematters.presentation.base.Event

sealed class HomeEvent: Event {

    class LoadData(val home: Home): HomeEvent()
    class ClickUser(val user: User, val home: Home): HomeEvent()
    class AddUser(val home: Home): HomeEvent()
    class EditHomeData(val home: Home): HomeEvent()

}