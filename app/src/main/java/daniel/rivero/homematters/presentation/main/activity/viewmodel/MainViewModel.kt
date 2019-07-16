package daniel.rivero.homematters.presentation.main.activity.viewmodel

import androidx.annotation.IdRes
import daniel.rivero.homematters.R
import daniel.rivero.homematters.domain.Home
import daniel.rivero.homematters.domain.User
import daniel.rivero.homematters.domain.service.PreferenceService
import daniel.rivero.homematters.infrastructure.AndroidApplication
import daniel.rivero.homematters.presentation.base.BaseViewModel
import daniel.rivero.homematters.presentation.main.activity.viewstate.MainViewState
import daniel.rivero.homematters.presentation.main.activity.event.MainEvent
import javax.inject.Inject

class MainViewModel @Inject constructor(
    app: AndroidApplication,
    private val preferenceService: PreferenceService
) : BaseViewModel<MainViewState, MainEvent>(app) {

    private lateinit var user: User

    override fun onEvent(event: MainEvent) {
        when (event) {
            MainEvent.Initialize -> loadInitialData()
            is MainEvent.Navigation -> navigate(event.menuAction)
        }
    }

    private fun loadInitialData() {
        val home = Home("qx15I71iWc4uzME7t4Vl", "Torrejón", "def8RPMrh02u3xMyu0KJ")
        user = User("def8RPMrh02u3xMyu0KJ", "Daniel", "danielrl.drl@gmail.com", "qx15I71iWc4uzME7t4Vl", 15)
        preferenceService.setUser(user)
        preferenceService.setDefaultHome(home)
    }

    private fun navigate(@IdRes menuAction: Int) {
        when(menuAction) {
            R.id.item_group -> showHomeSettings(preferenceService.getDefaultHome())
            R.id.item_task_list -> navigator.showTaskList()
            R.id.item_calendar -> navigator.showCalendar()
            R.id.item_chart -> navigator.showChart()
            R.id.item_profile -> navigator.showUserDetail(user, true)
        }
    }

    private fun showHomeSettings(home: Home?) {
        home?.let {
            navigator.showHomeSettings(it)
        } ?: navigator.showEmptyHome()
    }

}