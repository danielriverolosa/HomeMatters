package daniel.rivero.homematters.presentation.main.home.viewstate

import daniel.rivero.homematters.domain.Home
import daniel.rivero.homematters.domain.User
import daniel.rivero.homematters.presentation.base.ViewState

sealed class HomeViewState: ViewState {

    class LoadHomeData(val home: Home): HomeViewState()
    class LoadUsersData(val userList: List<User>, val totalEffort: Int): HomeViewState()

}