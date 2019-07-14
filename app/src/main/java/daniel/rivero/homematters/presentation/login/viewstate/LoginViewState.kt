package daniel.rivero.homematters.presentation.login.viewstate

import daniel.rivero.homematters.presentation.base.ViewState

sealed class LoginViewState : ViewState {
    object Loading : LoginViewState()
    object InvalidCredentials : LoginViewState()
    class ValidationError(val validationResult: List<String>) : LoginViewState()
}