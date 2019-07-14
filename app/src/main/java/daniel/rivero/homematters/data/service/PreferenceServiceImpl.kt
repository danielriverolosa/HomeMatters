package daniel.rivero.homematters.data.service

import android.content.SharedPreferences
import daniel.rivero.homematters.domain.Home
import daniel.rivero.homematters.domain.User
import daniel.rivero.homematters.domain.service.PreferenceService
import javax.inject.Inject

class PreferenceServiceImpl @Inject constructor(
    sharedPreferences: SharedPreferences
): BasePreferenceService(sharedPreferences), PreferenceService {
    override fun setUser(user: User) {
        setObject(Key.USER, user)
    }

    override fun getUser(): User? {
       return getObject(Key.USER, User::class.java)
    }

    override fun setDefaultHome(home: Home) {
        setObject(Key.DEFAULT_HOUSE, home)
    }

    override fun getDefaultHome(): Home? {
        return getObject(Key.DEFAULT_HOUSE, Home::class.java)
    }

    private enum class Key : PreferencesKey {
        USER,
        DEFAULT_HOUSE;

        override fun keyString(): String {
            return this.name
        }

    }

}