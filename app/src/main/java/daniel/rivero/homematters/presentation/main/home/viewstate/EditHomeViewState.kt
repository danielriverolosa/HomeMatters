package daniel.rivero.homematters.presentation.main.home.viewstate

import daniel.rivero.homematters.domain.User
import daniel.rivero.homematters.presentation.base.ViewState

sealed class EditHomeViewState : ViewState {

    object Loading : EditHomeViewState()
    class LoadData(val homeName: String, val admin: User, val userList: List<User>) : EditHomeViewState()

}