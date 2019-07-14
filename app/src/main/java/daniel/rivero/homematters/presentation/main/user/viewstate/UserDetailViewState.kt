package daniel.rivero.homematters.presentation.main.user.viewstate

import daniel.rivero.homematters.domain.User
import daniel.rivero.homematters.presentation.base.ViewState

sealed class UserDetailViewState: ViewState {

    class LoadData(val user: User) : UserDetailViewState()
    object DeleteHomeLoading: UserDetailViewState()
    object HomeDeleted : UserDetailViewState()
    class GenericError(val message: String?): UserDetailViewState()

}