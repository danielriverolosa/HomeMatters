package daniel.rivero.homematters.presentation.main.user.viewmodel

import android.annotation.SuppressLint
import daniel.rivero.homematters.domain.User
import daniel.rivero.homematters.domain.UserEffort
import daniel.rivero.homematters.domain.interactor.user.edit.EditUserDto
import daniel.rivero.homematters.domain.interactor.user.edit.EditUserUseCase
import daniel.rivero.homematters.infrastructure.AndroidApplication
import daniel.rivero.homematters.presentation.base.BaseViewModel
import daniel.rivero.homematters.presentation.common.utils.EffortResolver
import daniel.rivero.homematters.presentation.main.user.event.EditUserEvent
import daniel.rivero.homematters.presentation.main.user.viewstate.EditUserViewState
import javax.inject.Inject

class EditUserViewModel @Inject constructor(
    app: AndroidApplication,
    private val editUserUseCase: EditUserUseCase
): BaseViewModel<EditUserViewState, EditUserEvent>(app) {

    private lateinit var user: User

    override fun onEvent(event: EditUserEvent) {
        when(event) {
            is EditUserEvent.Initialize -> loadData(event.user)
            is EditUserEvent.EffortChange -> onEffortChange(event.value)
            is EditUserEvent.Continue -> onClickContinue(event.name, event.value)
        }
    }

    private fun loadData(user: User) {
        this.user = user
        updateViewState(EditUserViewState.LoadInitialData(user.name, UserEffort.getEffortFromValue(user.weeklyEffort)))
    }

    private fun onEffortChange(value: Int) {
        val effort = EffortResolver.getUserEffort(value)
        updateViewState(EditUserViewState.OnEffortChange(effort))
    }

    @SuppressLint("CheckResult")
    private fun onClickContinue(name: String, value: Int) {
        updateViewState(EditUserViewState.Loading)
        val effort = EffortResolver.getUserEffort(value)

        editUserUseCase(EditUserDto(user.id, name, effort))
            .subscribe(::onComplete, ::onError)

    }

    private fun onComplete() {
        updateViewState(EditUserViewState.Close)
    }

    private fun onError(t: Throwable) {
        updateViewState(EditUserViewState.EditUserError(t.message))
    }

}