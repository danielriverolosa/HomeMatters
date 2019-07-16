package daniel.rivero.homematters.presentation.main.home.viewmodel

import android.annotation.SuppressLint
import daniel.rivero.homematters.domain.Home
import daniel.rivero.homematters.domain.User
import daniel.rivero.homematters.domain.interactor.user.list.GetUserListByHomeUseCase
import daniel.rivero.homematters.domain.service.PreferenceService
import daniel.rivero.homematters.infrastructure.AndroidApplication
import daniel.rivero.homematters.presentation.base.BaseViewModel
import daniel.rivero.homematters.presentation.main.home.event.HomeEvent
import daniel.rivero.homematters.presentation.main.home.viewstate.HomeViewState
import timber.log.Timber
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    app: AndroidApplication,
    private val preferenceService: PreferenceService,
    private val userListUseCase: GetUserListByHomeUseCase
): BaseViewModel<HomeViewState, HomeEvent>(app) {

    private lateinit var userList: List<User>

    override fun onEvent(event: HomeEvent) {
        when(event) {
            is HomeEvent.LoadData -> loadData(event.home)
            is HomeEvent.AddUser -> navigator.showAddUserToHome(event.home)
            is HomeEvent.EditHomeData -> navigator.showEditHome(event.home, userList)
            is HomeEvent.ClickUser -> navigator.showUserDetail(event.user, false)
        }
    }

    private fun loadData(home: Home?) {
        home?.let {
            updateViewState(HomeViewState.LoadHomeData(it))
            loadUserList(it)
        } ?: navigator.showEmptyHome()
    }

    @SuppressLint("CheckResult")
    private fun loadUserList(home: Home) {
        userListUseCase(home.id)
            .subscribe(::onSuccess) {
                //TODO: Show error view
            }
    }

    private fun onSuccess(userList: List<User>) {
        Timber.e("User list: $userList")
        this.userList = userList
        val totalEffort = userList.sumBy { it.weeklyEffort }
        updateViewState(HomeViewState.LoadUsersData(userList, totalEffort))
    }

}