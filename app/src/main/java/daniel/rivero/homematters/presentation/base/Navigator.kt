package daniel.rivero.homematters.presentation.base

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import daniel.rivero.homematters.R
import daniel.rivero.homematters.domain.User
import daniel.rivero.homematters.presentation.login.fragment.LoginFragment
import daniel.rivero.homematters.presentation.login.fragment.SignUpFragment
import daniel.rivero.homematters.presentation.main.activity.MainActivity
import daniel.rivero.homematters.presentation.main.calendar.fragment.CalendarFragment
import javax.inject.Inject


class Navigator @Inject constructor(private val context: Context) {

    private val fragmentManager: FragmentManager
        get() = (context as AppCompatActivity).supportFragmentManager

    private fun showFragment(containerId: Int, fragment: Fragment, backEnable: Boolean) {
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(containerId, fragment)

        if (backEnable) {
            val fragmentTag = retrieveFragmentTag(fragment)
            transaction.addToBackStack(fragmentTag)
        }

        transaction.commit()
    }

    private fun retrieveFragmentTag(fragment: Fragment?): String? {
        return fragment?.javaClass?.simpleName
    }

    fun showLogin() {
        showFragment(R.id.fragmentContainer, LoginFragment.getInstance(), false)
    }

    fun showSignUp() {
        showFragment(R.id.fragmentContainer, SignUpFragment.getInstance(), true)
    }

    fun showRecoveryPassword() {

    }

    fun goToMain(user: User) {
        context.startActivity(MainActivity.getIntent(context, user))
    }

    fun showHomeSettings(home: Home) {

    }

    fun showTaskList() {
    }

    fun showCalendar() {
        showFragment(R.id.fragmentContainer, CalendarFragment.getInstance(), false)
    }

    fun showChart() {

    }

    fun showEmptyHome() {

    }

    fun showUserDetail(user: User, showEditMode: Boolean) {

    }
