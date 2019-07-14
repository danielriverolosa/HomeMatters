package daniel.rivero.homematters.presentation.login.event

import daniel.rivero.homematters.presentation.base.Event

sealed class AuthEvent : Event {
    object Login : AuthEvent()
    object SignUp: AuthEvent()
}