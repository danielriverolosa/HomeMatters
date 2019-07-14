package daniel.rivero.homematters.domain.service

import daniel.rivero.homematters.domain.Home
import daniel.rivero.homematters.domain.User

interface PreferenceService {

    fun setUser(user: User)

    fun getUser(): User?

    fun setDefaultHome(home: Home)

    fun getDefaultHome(): Home?

}