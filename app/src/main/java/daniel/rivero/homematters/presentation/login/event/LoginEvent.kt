package daniel.rivero.homematters.presentation.login.event

import daniel.rivero.homematters.presentation.base.Event

sealed class LoginEvent: Event {
    object SignUp: LoginEvent()
    object RecoveryPassword: LoginEvent()
    class Continue(val email: String, val password: String): LoginEvent()
}