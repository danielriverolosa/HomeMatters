package daniel.rivero.homematters.infrastructure.di.component

import daniel.rivero.homematters.infrastructure.di.module.ViewModule
import daniel.rivero.homematters.infrastructure.di.scope.ViewScope
import dagger.Subcomponent
import daniel.rivero.homematters.presentation.login.AuthActivity
import daniel.rivero.homematters.presentation.login.fragment.LoginFragment
import daniel.rivero.homematters.presentation.login.fragment.SignUpFragment
import daniel.rivero.homematters.presentation.main.activity.MainActivity
import daniel.rivero.homematters.presentation.main.calendar.fragment.CalendarFragment
import daniel.rivero.homematters.presentation.main.home.fragment.*
import daniel.rivero.homematters.presentation.main.task.fragment.*
import daniel.rivero.homematters.presentation.main.user.fragment.EditUserFragment
import daniel.rivero.homematters.presentation.main.user.fragment.UserDetailFragment


@ViewScope
@Subcomponent(
        modules = [
            ViewModule::class
        ]
)
interface ViewComponent {

    fun inject(activity: AuthActivity)

    fun inject(fragment: LoginFragment)
    fun inject(fragment: SignUpFragment)

    fun inject(activity: MainActivity)

    fun inject(fragment: CalendarFragment)

    fun inject(fragment: HomeFragment)
    fun inject(fragment: HomeUnselectedFragment)
    fun inject(fragment: CreateHomeFragment)
    fun inject(fragment: EditHomeFragment)
    fun inject(fragment: AddUserFragment)

    fun inject(fragment: UserDetailFragment)
    fun inject(fragment: EditUserFragment)

    fun inject(fragment: TaskManagerFragment)
    fun inject(fragment: TaskListPageFragment)
    fun inject(fragment: AddTaskSelectorModeFragment)
    fun inject(fragment: CreateTaskFragment)
    fun inject(fragment: TaskListSelectorFragment)
    fun inject(fragment: TaskListSelectorPageFragment)
    fun inject(fragment: TaskDetailFragment)

}