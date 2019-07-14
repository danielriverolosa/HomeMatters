package daniel.rivero.homematters.presentation.base

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import daniel.rivero.homematters.R
import daniel.rivero.homematters.domain.Home
import daniel.rivero.homematters.domain.Task
import daniel.rivero.homematters.domain.User
import daniel.rivero.homematters.presentation.login.fragment.LoginFragment
import daniel.rivero.homematters.presentation.login.fragment.SignUpFragment
import daniel.rivero.homematters.presentation.main.activity.MainActivity
import daniel.rivero.homematters.presentation.main.calendar.fragment.CalendarFragment
import daniel.rivero.homematters.presentation.main.home.fragment.*
import daniel.rivero.homematters.presentation.main.stats.fragment.ChartFragment
import daniel.rivero.homematters.presentation.main.task.fragment.*
import daniel.rivero.homematters.presentation.main.user.fragment.EditUserFragment
import daniel.rivero.homematters.presentation.main.user.fragment.UserDetailFragment
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
        showFragment(R.id.fragmentContainer, TaskManagerFragment.getInstance(), false)
    }

    fun showCalendar() {
        showFragment(R.id.fragmentContainer, CalendarFragment.getInstance(), false)
    }

    fun showChart() {
        showFragment(R.id.fragmentContainer, ChartFragment.getInstance(), false)
    }

    fun showUserDetail(user: User, showEditMode: Boolean) {
        showFragment(R.id.fragmentContainer, UserDetailFragment.getInstance(user, showEditMode), false)
    }

    fun showEmptyHome() {
        showFragment(R.id.fragmentContainer, HomeUnselectedFragment.getInstance(), false)
    }

    fun showCreateHome() {
        showFragment(R.id.fragmentContainer, CreateHomeFragment.getInstance(), false)
    }

    fun showAddUserToHome(home: Home) {
        showFragment(R.id.fragmentContainer, AddUserFragment.getInstance(home), true)
    }

    fun showEditHome(home: Home, userList: List<User>) {
        showFragment(R.id.fragmentContainer, EditHomeFragment.getInstance(userList, home), true)
    }

    fun showEditAccount(user: User) {
        showFragment(R.id.fragmentContainer, EditUserFragment.getInstance(user), true)
    }

    fun showAddTaskSelectorMode() {
        showFragment(R.id.fragmentContainer, AddTaskSelectorModeFragment.getInstance(), false)
    }

    fun showCreateTask() {
        showFragment(R.id.fragmentContainer, CreateTaskFragment.getInstance(), true)
    }

    fun showExistingTask() {
        showFragment(R.id.fragmentContainer, TaskListSelectorFragment.getInstance(), true)
    }

    fun showScheduleTask(task: Task) {
        showFragment(R.id.fragmentContainer, TaskDetailFragment.getInstance(task), true)
    }

}