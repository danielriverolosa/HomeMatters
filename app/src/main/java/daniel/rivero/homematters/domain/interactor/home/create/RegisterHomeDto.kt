package daniel.rivero.homematters.domain.interactor.home.create

import daniel.rivero.homematters.domain.User

class RegisterHomeDto(
    val name: String,
    var admin: User? = null
)