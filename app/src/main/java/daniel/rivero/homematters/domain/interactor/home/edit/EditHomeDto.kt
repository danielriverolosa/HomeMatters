package daniel.rivero.homematters.domain.interactor.home.edit

import daniel.rivero.homematters.domain.Home
import daniel.rivero.homematters.domain.User

class EditHomeDto(
    val id: String,
    val newName: String,
    val admin: User
)