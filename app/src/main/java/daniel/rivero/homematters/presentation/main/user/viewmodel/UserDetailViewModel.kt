package daniel.rivero.homematters.presentation.main.user.viewmodel

import android.annotation.SuppressLint
import daniel.rivero.homematters.domain.User
import daniel.rivero.homematters.domain.interactor.home.delete.DeleteHomeToUserDto
import daniel.rivero.homematters.domain.interactor.home.delete.DeleteHomeToUserUseCase
import daniel.rivero.homematters.infrastructure.AndroidApplication
import daniel.rivero.homematters.presentation.base.BaseViewModel
import daniel.rivero.homematters.presentation.main.user.event.UserDetailEvent
import daniel.rivero.homematters.presentation.main.user.viewstate.UserDetailViewState
import javax.inject.Inject

class UserDetailViewModel @Inject constructor(
    app: AndroidApplication,
    private val deleteHomeToUserUseCase: DeleteHomeToUserUseCase
): BaseViewModel<UserDetailViewState, UserDetailEvent>(app) {

    private lateinit var user: User

    override fun onEvent(event: UserDetailEvent) = when(event) {
        is UserDetailEvent.Initialize -> loadInitialData(event.user)
        is UserDetailEvent.EditAccount -> navigator.showEditAccount(user)
        UserDetailEvent.DeleteHome -> deleteHome()
    }

    private fun loadInitialData(user: User) {
        this.user = user
        updateViewState(UserDetailViewState.LoadData(user))
    }

    @SuppressLint("CheckResult")
    private fun deleteHome() {
        deleteHomeToUserUseCase(DeleteHomeToUserDto(user))
            .subscribe(::onComplete, ::onError)
    }

    private fun onComplete() {
        updateViewState(UserDetailViewState.HomeDeleted)
    }

    private fun onError(t: Throwable) {
        updateViewState(UserDetailViewState.GenericError(t.message))
    }

}