package daniel.rivero.homematters.presentation.login.event

import daniel.rivero.homematters.presentation.base.Event

sealed class SignUpEvent: Event {

    object Login: SignUpEvent()
    class Continue(val name: String, val email: String, val password: String, val confirmPassword: String): SignUpEvent()

}