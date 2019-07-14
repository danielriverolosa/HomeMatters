package daniel.rivero.homematters.presentation.main.user.viewstate

import daniel.rivero.homematters.domain.UserEffort
import daniel.rivero.homematters.presentation.base.ViewState

sealed class EditUserViewState: ViewState {

    class LoadInitialData(val name: String, val effort: UserEffort?): EditUserViewState()
    class OnEffortChange(val effort: UserEffort): EditUserViewState()
    object Loading: EditUserViewState()
    object Close: EditUserViewState()
    class EditUserError(val message: String?) : EditUserViewState()
}