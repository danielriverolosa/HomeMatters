package daniel.rivero.homematters.presentation.main.home.viewstate

import daniel.rivero.homematters.domain.User
import daniel.rivero.homematters.presentation.base.ViewState

sealed class AddUserViewState: ViewState {

    class LoadUserData(val user: User): AddUserViewState()
    object UserAdded: AddUserViewState()
    object UserNotFound: AddUserViewState()
    object UserAlreadyExists: AddUserViewState()
    object GenericError: AddUserViewState()
    object SearchLoading: AddUserViewState()
    object AddUserLoading: AddUserViewState()

}