package daniel.rivero.homematters.domain.interactor.user.edit

import daniel.rivero.homematters.domain.UserEffort

class EditUserDto(
    val id: String,
    val name: String,
    val effort: UserEffort
)