package daniel.rivero.homematters.domain.interactor.user.edit

import daniel.rivero.homematters.domain.User
import daniel.rivero.homematters.domain.UserEffort

class EditUserDto(
    val user: User,
    val name: String,
    val effort: UserEffort
)