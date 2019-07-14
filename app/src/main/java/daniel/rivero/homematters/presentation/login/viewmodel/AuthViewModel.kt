package daniel.rivero.homematters.presentation.login.viewmodel

import daniel.rivero.homematters.infrastructure.AndroidApplication
import daniel.rivero.homematters.presentation.base.BaseViewModel
import daniel.rivero.homematters.presentation.login.event.AuthEvent
import daniel.rivero.homematters.presentation.login.viewstate.AuthViewState
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    app: AndroidApplication
): BaseViewModel<AuthViewState, AuthEvent>(app) {
    override fun onEvent(event: AuthEvent) {
        when(event) {
            AuthEvent.Login -> navigator.showLogin()
            AuthEvent.SignUp -> navigator.showSignUp()
        }
    }
}