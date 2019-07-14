package daniel.rivero.homematters.presentation.base

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import daniel.rivero.homematters.R
import daniel.rivero.homematters.domain.Home
import daniel.rivero.homematters.domain.User
import daniel.rivero.homematters.presentation.login.fragment.LoginFragment
import daniel.rivero.homematters.presentation.login.fragment.SignUpFragment
import daniel.rivero.homematters.presentation.main.activity.MainActivity
import daniel.rivero.homematters.presentation.main.calendar.fragment.CalendarFragment
import daniel.rivero.homematters.presentation.main.home.fragment.*
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
        showFragment(R.id.fragmentContainer, HomeFragment.getInstance(home), false)
    }

    fun showTaskList() {
    }

    fun showCalendar() {
        showFragment(R.id.fragmentContainer, CalendarFragment.getInstance(), false)
    }

    fun showChart() {

    }

    fun showEmptyHome() {
        showFragment(R.id.fragmentContainer, HomeUnselectedFragment.getInstance(), false)
    fun showCreateHome() {
        showFragment(R.id.fragmentContainer, CreateHomeFragment.getInstance(), false)
    }

    }

    fun showUserDetail(user: User, showEditMode: Boolean) {

    }
