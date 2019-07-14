package daniel.rivero.homematters.presentation.main.home.viewmodel

import android.annotation.SuppressLint
import daniel.rivero.homematters.domain.Home
import daniel.rivero.homematters.domain.User
import daniel.rivero.homematters.domain.interactor.home.edit.EditHomeDto
import daniel.rivero.homematters.domain.interactor.home.edit.EditHomeUseCase
import daniel.rivero.homematters.infrastructure.AndroidApplication
import daniel.rivero.homematters.presentation.base.BaseViewModel
import daniel.rivero.homematters.presentation.main.home.event.EditHomeEvent
import daniel.rivero.homematters.presentation.main.home.viewstate.EditHomeViewState
import javax.inject.Inject

class EditHomeViewModel @Inject constructor(
    app: AndroidApplication,
    private val editHomeUseCase: EditHomeUseCase
): BaseViewModel<EditHomeViewState, EditHomeEvent>(app) {

    override fun onEvent(event: EditHomeEvent) {
        when(event) {
            is EditHomeEvent.LoadData -> loadInitialData(event.home, event.userList)
            is EditHomeEvent.Continue -> onClickContinue(
                EditHomeDto(
                    event.homeName,
                    event.user
                )
            )
        }
    }

    private fun loadInitialData(home: Home, userList: List<User>) {
        val admin = userList.first { it.id == home.admin.id }
        updateViewState(EditHomeViewState.LoadData(home.name, admin, userList))
    }

    @SuppressLint("CheckResult")
    private fun onClickContinue(dto: EditHomeDto) {
        updateViewState(EditHomeViewState.Loading)
        editHomeUseCase(dto).subscribe(::onSuccess, ::onError)
    }

    private fun onSuccess(home: Home) {
        navigator.showHomeSettings(home)
    }

    private fun onError(t: Throwable) {
        //TODO: manage error
    }

}