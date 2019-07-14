package daniel.rivero.homematters.presentation.main.home.viewmodel

import daniel.rivero.homematters.infrastructure.AndroidApplication
import daniel.rivero.homematters.presentation.base.BaseViewModel
import daniel.rivero.homematters.presentation.main.home.event.HomeUnselectedEvent
import daniel.rivero.homematters.presentation.main.home.viewstate.HomeUnselectedViewState
import javax.inject.Inject

class HomeUnselectedViewModel @Inject constructor(
    app: AndroidApplication
): BaseViewModel<HomeUnselectedViewState, HomeUnselectedEvent>(app) {
    override fun onEvent(event: HomeUnselectedEvent) {
        when(event) {
            HomeUnselectedEvent.NewHome -> navigator.showCreateHome()
        }
    }
}