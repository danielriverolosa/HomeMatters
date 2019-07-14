package daniel.rivero.homematters.presentation.main.user.event

import daniel.rivero.homematters.domain.User
import daniel.rivero.homematters.presentation.base.Event

sealed class UserDetailEvent: Event {

    class Initialize(val user: User): UserDetailEvent()
    object EditAccount: UserDetailEvent()
    object DeleteHome: UserDetailEvent()

}